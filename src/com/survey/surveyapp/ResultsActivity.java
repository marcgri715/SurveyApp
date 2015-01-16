package com.survey.surveyapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ResultsActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		ListView listView1 = (ListView) findViewById(R.id.results_listView);
        
        List<String> items = new ArrayList<String>();// = DatabaseManager.getInstance().getAnswers();
        
        List<Question> questionsList = Result.getInstance().getQuestions();

        for (Question q : questionsList) {
        	String question = q.getContent();
        	List<Answer> answersList = q.getAnswers();
        	String answers = "";
        	for (Answer a : answersList) {
        		if (a.getValue()) {
	        		answers += '\n' + a.getContent();
        		}
        	}        		
        	items.add(question + answers);
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        listView1.setAdapter(adapter);
	}
	
	public void returnButtonClick(View view) {

	}
}
