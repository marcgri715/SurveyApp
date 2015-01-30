package com.survey.surveyapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatorActivity extends ActionBarActivity {

	private Button addQuestions;
	private EditText etTopic;
	private EditText etQuestionsCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creator);
		etTopic = (EditText)this.findViewById(R.id.et_topic);
		etQuestionsCount = (EditText)this.findViewById(R.id.et_questions_count);
		setupAddQuestionsButton();
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
	
	private void setupAddQuestionsButton() {
		addQuestions = (Button) findViewById(R.id.btn_add_questions);
		addQuestions.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkInsertedData()) {
					int questionsCount = Integer.parseInt(etQuestionsCount.getText().toString());				// Odczytuje iloœæ pytañ
					Result.getInstance().setContent(etTopic.getText().toString());								// Zapisuje temat
					Intent intent = new Intent(CreatorActivity.this, AddQuestionsActivity.class);				// MarcinoweActivity
					intent.putExtra("questionsCount", questionsCount);											// Przesy³am iloœæ pytañ
					CreatorActivity.this.startActivity(intent);
				}
			}
		});
	}
	
	private boolean checkInsertedData() {
        String topic = etTopic.getText().toString();
        try {
        	int count = Integer.parseInt(etQuestionsCount.getText().toString());
	        if (topic.equals("") || topic.equals("Podaj temat ankiety..")) {
	        	Toast.makeText(null, "Nie podano tematu ankiety!", Toast.LENGTH_SHORT).show();	// NULL?
	        	return false;
	        }
	        if (count < 1) {
	        	Toast.makeText(null, "Nieprawid³owa wartoœæ w polu iloœæ pytañ!", Toast.LENGTH_SHORT).show();	// NULL?
	        	return false;
	        }
	        return true;
        }
        catch (NumberFormatException ex) {
        	Toast.makeText(null, "Pole iloœæ pytañ wymaga podania wartoœci numerycznej!", Toast.LENGTH_SHORT).show();	// NULL?
        	return false;
        }
	}
}
