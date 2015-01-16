package com.survey.surveyapp;

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
			Intent intent = new Intent(this, ResultsActivity.class);
			this.startActivity(intent);
			finish();
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
