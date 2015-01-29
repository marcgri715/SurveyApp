package com.survey.surveyapp;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class AnswerItemAdapter extends ArrayAdapter<SingleAnswerItem>{

	private Context context;
    private int layoutResourceId;  
    
    public AnswerItemAdapter(Context _context, int _layoutResourceId) {
        super(_context, _layoutResourceId);
        this.layoutResourceId = _layoutResourceId;
        this.context = _context;

    }
    @Override
   public View getView(int position, View convertView, ViewGroup parent){

    	SingleAnswerItem item = new SingleAnswerItem();
    	View row = convertView;
    	LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        
        return row;

   }

}
