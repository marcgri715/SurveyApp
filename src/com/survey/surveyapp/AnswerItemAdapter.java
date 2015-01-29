package com.survey.surveyapp;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class AnswerItemAdapter extends ArrayAdapter<Answer> implements OnKeyListener{

	private Context context;
    private List<Answer> layoutResourceId;
	private CheckBox chk_answer;
	private EditText answerContent;
    
    public AnswerItemAdapter(Context _context, List<Answer> _layoutResourceId) {
        super(_context, R.layout.activity_creator_answer, _layoutResourceId);
        this.layoutResourceId = _layoutResourceId;
        this.context = _context;

    }
    @Override
   public View getView(int position, View convertView, ViewGroup parent){

		Answer holder = null;
    	View row = convertView;
    	LayoutInflater inflater = ((Activity) context).getLayoutInflater();
    	row = inflater.inflate(R.layout.activity_creator_answer, parent, false);
    	holder = getItem(position);
    	answerContent = (EditText) row.findViewById(R.id.et_answer);
        chk_answer = (CheckBox) row.findViewById(R.id.chk_answer);
        chk_answer.setChecked(holder.getValue());
        chk_answer.setText(holder.getContent());
        chk_answer.setTag(position);
        chk_answer.setOnClickListener(new CheckBox.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				CheckBox clicked = (CheckBox) v;
				layoutResourceId.get((Integer)v.getTag()).setValue(clicked.isChecked());
			}
		});
        holder.setId(position);
        
        
        return row;

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

}
