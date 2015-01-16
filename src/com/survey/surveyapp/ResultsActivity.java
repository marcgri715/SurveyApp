package com.survey.surveyapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultsActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		ListView listView1 = (ListView) findViewById(R.id.results_listView);
        
        String[] items = {  };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, items);
        
        listView1.setAdapter(adapter);
	}
	
	
	
}
