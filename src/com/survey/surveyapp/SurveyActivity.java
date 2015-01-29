package com.survey.surveyapp;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends ActionBarActivity {
	private ListAdapter adapter;
	private Button nextBtn;
	private int questionIndex;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_survey);
		Intent intent = getIntent();
		questionIndex = 0;
		adapter = new SurveyAdapter(context, Result
				.getInstance().getQuestion(questionIndex).getAnswers());
		ListView list = (ListView) findViewById(R.id.question_listView);
		list.setAdapter(adapter);
		TextView questionContext = (TextView) findViewById(R.id.editText1);
		questionContext.setText(Result.getInstance().getQuestion(questionIndex).getContent());
	}

	public void prevButtonClick(View view) {
		if (questionIndex > 0) {
			Result.getInstance().getQuestion(questionIndex).setAnsweredFlag(false);
			for (Answer a : Result.getInstance().getQuestion(questionIndex).getAnswers()) {
				if (a.getValue()) {
					Result.getInstance().getQuestion(questionIndex).setAnsweredFlag(true);
					break;
				}
			}
			questionIndex--;
			adapter = new SurveyAdapter(context, Result
					.getInstance().getQuestion(questionIndex).getAnswers());
			ListView list = (ListView) findViewById(R.id.question_listView);
			list.setAdapter(adapter);
			TextView questionContext = (TextView) findViewById(R.id.editText1);
			questionContext.setText(Result.getInstance().getQuestion(questionIndex).getContent());
			Button button = (Button) findViewById(R.id.next_question_btn);
			button.setText("Nastêpne");
		}
	}
	
	public void nextButtonClick(View view) {
		Result.getInstance().getQuestion(questionIndex).setAnsweredFlag(false);
		for (Answer a : Result.getInstance().getQuestion(questionIndex).getAnswers()) {
			if (a.getValue()) {
				Result.getInstance().getQuestion(questionIndex).setAnsweredFlag(true);
				break;
			}
		}
		if (questionIndex < Result.getInstance().getNumberOfQuestions()-1) {
			questionIndex++;
			adapter = new SurveyAdapter(context, Result
					.getInstance().getQuestion(questionIndex).getAnswers());
			ListView list = (ListView) findViewById(R.id.question_listView);
			list.setAdapter(adapter);
			TextView questionContext = (TextView) findViewById(R.id.editText1);
			questionContext.setText(Result.getInstance().getQuestion(questionIndex).getContent());
			Button button = (Button) findViewById(R.id.next_question_btn);
			if (questionIndex == Result.getInstance().getNumberOfQuestions()-1) {
				button.setText("Zakoñcz");
			} else {
				button.setText("Nastêpne");
			}
		} else if (questionIndex == Result.getInstance().getNumberOfQuestions()-1) {
			boolean isFinished = true;
			for (Question q : Result.getInstance().getQuestions()) {
				if (!q.isAnswered()) {
					isFinished = false;
					break;
				}
			}
			if (isFinished) {
				Intent intent = new Intent(this, ResultsActivity.class);
				this.startActivity(intent);
				DatabaseManager.getInstance().open(this);
				DatabaseManager.getInstance().insertKeys();
				DatabaseManager.getInstance().close();
				finish();
			} else {
				Toast.makeText(this, "Nie odpowiedziano na wszystkie pytania!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.survey, menu);
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
