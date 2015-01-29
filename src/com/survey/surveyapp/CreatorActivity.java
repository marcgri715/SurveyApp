package com.survey.surveyapp;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.Toast;

public class CreatorActivity extends ActionBarActivity implements OnKeyListener {

	private ArrayAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creator_main);
		Result.getInstance().setContent("");
		List<Question> qs = new ArrayList<Question>();
		qs.add(new Question());
		List<Answer> as = new ArrayList<Answer>();
		as.add(new Answer());
		qs.get(0).setAnswers(as);
		Result.getInstance().setQuestions(qs);
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
	
	public void finishSurveyButton (View view) {
		DatabaseManager.getInstance().open(view.getContext());
		long id = DatabaseManager.getInstance().addNewSurveyTopic(Result.getInstance().getContent());
		for (Question q : Result.getInstance().getQuestions()) {
			String[] answ = new String[q.getNumberOfAnswers()];
			for (Answer a : q.getAnswers()) {
				answ[a.getId()] = a.getContent();
			}
			QuestionObject question = new QuestionObject(q.getContent(), answ);
			DatabaseManager.getInstance().addNewQuestion(id, question);
		}
		DatabaseManager.getInstance().close();
		Toast.makeText(view.getContext(), "UDA�O MI SI�!", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	public void addNewQuestionButton (View view) {
		Result.getInstance().getQuestions().add(new Question());
		int index = Result.getInstance().getNumberOfQuestions();
		Result.getInstance().getQuestion(index).setAnswers(new ArrayList<Answer>(1));
		//wystarczy?
		adapter.notifyDataSetChanged();
	}
	
	public void deleteQuestionsButton (View view) {
		int deleted = 0;
		for (Question q : Result.getInstance().getQuestions()) {
			if (q.isAnswered()) {
				Result.getInstance().getQuestions().remove(q.getId() - deleted++);
			}
		}
		adapter.notifyDataSetChanged();
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
