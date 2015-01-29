package com.survey.surveyapp;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class CreatorActivity extends ActionBarActivity implements OnKeyListener {

	private ArrayAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creator_main);
		Result.getInstance().setContent("");
		Result.getInstance().setQuestions(new ArrayList<Question>(1));
		Result.getInstance().getQuestion(0).setAnswers(new ArrayList<Answer>(1));
		EditText editText = (EditText) findViewById(R.id.et_topic);
		editText.setOnKeyListener(this);
		ListView listView = (ListView) findViewById(R.id.questionsList);
		adapter = new QuestionItemAdapter(this, Result.getInstance().getQuestions());
		listView.setAdapter(adapter);
	}
	
	@Override
	public boolean onKey(View view, int keyCode, KeyEvent event) {
		EditText editText = (EditText) view;
		if (keyCode == EditorInfo.IME_ACTION_DONE ||
		 keyCode == EditorInfo.IME_ACTION_SEARCH || 
		 event.getAction() == KeyEvent.ACTION_DOWN &&
		 event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
			Result.getInstance().setContent(editText.getText().toString());
			return true;
		} else {
			return false;
		}
	}
	
	public void addNewQuestionButton (View view) {
		Result.getInstance().getQuestions().add(new Question());
		int index = Result.getInstance().getNumberOfQuestions();
		Result.getInstance().getQuestion(index).setAnswers(new ArrayList<Answer>(1));
		//wystarczy?
		adapter.notifyDataSetChanged();
	}
	
	public void deleteQuestionsButton (View view) {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creator, menu);
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
