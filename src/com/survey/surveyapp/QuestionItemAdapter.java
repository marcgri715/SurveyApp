package com.survey.surveyapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class QuestionItemAdapter extends ArrayAdapter<Question> {

	private Button btn_add;
	private Button btn_remove;
	private CheckBox chk_question;
	private EditText et_question;
	private List<Boolean> CheckedAnswers;
	private ListView lv_answers; 
	ArrayAdapter adapter;
	
	public QuestionItemAdapter(Context _context, List<Question> _questions) {
		super(_context, R.layout.activity_creator_question, _questions);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		adapter = new AnswerItemAdapter(this.getContext(), Result.getInstance().getQuestion(position).getAnswers());
        lv_answers = (ListView)row.findViewById(R.id.answersList);
        lv_answers.setAdapter(adapter);
		LayoutInflater inflater = ((Activity) context).getLayoutInflate();
        
		btn_add = (Button)row.findViewById(R.id.btn_add_answer);
		btn_remove = (Button)row.findViewById(R.id.btn_remove_answers);
		chk_question = (CheckBox)row.findViewById(R.id.chk_question);
		et_question = (EditText)row.findViewById(R.id.et_question);
		//Result.getInstance().getQuestion(index);
		setupRemoveButton();		
		return row;
	}
	

	public void setupRemoveButton() {
		btn_remove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				List<Answer> answers = Result.getInstance().getQuestion(v.getId()).getAnswers();	// getId?
				int deleted = 0;
				for (Answer a : answers) {
					if (a.getValue()) {
						answers.remove(a.getId() - deleted++);	// getId?
						adapter.remove(a);
					}
				}
			}
		});
	}
	
	public void setupAddButton() {
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Result.getInstance().getQuestion(v.getId()).getAnswers().add(new Answer());	// getId()?
			}
		});
	}
	
	public void getCheckedAnswers() {
		
	}
}
