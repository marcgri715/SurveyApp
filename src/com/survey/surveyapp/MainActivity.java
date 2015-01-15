package com.survey.surveyapp;

import android.support.v7.app.ActionBarActivity;

import java.util.List;
import java.util.ArrayList;

import android.content.Intent;
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
		this.deleteDatabase("Survey.db");
		Database dbHelper = new Database(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.close();
		setupStartButton();
		//zaœlepka pod pytania
		setupQuestions();
	}
	
	private void setupQuestions() {
		List<Question> qlist = new ArrayList<Question>();
		for (int i=0; i<3; i++) {
			Question q = new Question();
			q.setContext("Pytanie " + i);
			List<Answer> alist = new ArrayList<Answer>();
			for (int j=0; j<4; j++) {
				Answer a = new Answer();
				a.setContext("OdpowiedŸ " +j);
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
