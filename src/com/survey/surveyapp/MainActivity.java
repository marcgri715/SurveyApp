package com.survey.surveyapp;

import android.support.v7.app.ActionBarActivity;

import java.util.List;
import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupStartButton();
		//zaœlepka pod pytania
		DatabaseManager.getInstance().open(this);
		DatabaseManager.getInstance().setupStartingQuestions();
		DatabaseManager.getInstance().close();
	}
	
	private void getQuestions() {
		int surveyIndex = Result.getInstance().getId(); //TODO
		List<Question> qlist = new ArrayList<Question>();
		this.deleteDatabase("Survey.db");
		Database dbHelper = new Database(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor questionCursor = db.query("Pytanie", null, "ID_Tem=?", new String[] {Integer.toString(surveyIndex)}, null, null, null);
		if (questionCursor.moveToFirst()) {
			do {
				Question q = new Question();
				q.setId(questionCursor.getInt(0));
				q.setContent(questionCursor.getString(2));
				List<Answer> alist = new ArrayList<Answer>();
				Cursor answerCursor = db.query("Odpowiedz", null, "ID_Pyt=?", new String[] {Integer.toString(q.getId())}, null, null, null);
				if (answerCursor.moveToFirst()) {
					do {
						Answer a = new Answer();
						a.setId(answerCursor.getInt(0));
						a.setContent(answerCursor.getString(2));
						alist.add(a);
					} while (answerCursor.moveToNext());
				}
				q.setAnswers(alist);
				answerCursor.close();
				qlist.add(q);
			} while (questionCursor.moveToNext());
		}
		questionCursor.close();
		Result.getInstance().setQuestions(qlist);
		db.close();
		dbHelper.close();
		for (int i=0; i<3; i++) {
			Question q = new Question();
			q.setContent("Pytanie " + i);
			List<Answer> alist = new ArrayList<Answer>();
			for (int j=0; j<4; j++) {
				Answer a = new Answer();
				a.setContent("OdpowiedŸ " +j);
				alist.add(a);
			}
			q.setAnswers(alist);
			qlist.add(q);
		}
		Result.getInstance().setQuestions(qlist);
	}

	private void setupStartButton() {
		startButton = (Button) findViewById(R.id.button1);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// rozpocznij zabawê
				getQuestions();
				Intent intent = new Intent(MainActivity.this,
						SurveyActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
