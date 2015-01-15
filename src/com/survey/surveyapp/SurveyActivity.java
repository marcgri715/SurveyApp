package com.survey.surveyapp;

import android.support.v7.app.ActionBarActivity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey);
		Intent intent = getIntent();
		questionIndex = intent.getExtras().getInt("questionIndex");
		if (questionIndex >= 0) {
			adapter = new SurveyAdapter(this, Result
					.getInstance().getQuestion(questionIndex).getAnswers());
			ListView list = (ListView) findViewById(R.id.question_listView);
			list.setAdapter(adapter);
			setupNextButton();
			TextView questionContext = (TextView) findViewById(R.id.editText1);
			questionContext.setText(Result.getInstance().getQuestion(questionIndex).getContext());
		}
	}

	private void setupNextButton() {
		nextBtn = (Button) findViewById(R.id.next_question_btn);
		nextBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
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
