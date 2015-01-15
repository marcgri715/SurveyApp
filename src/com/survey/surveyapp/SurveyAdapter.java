package com.survey.surveyapp;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class SurveyAdapter extends ArrayAdapter<Answer> {
	public List<Answer> itemList;
	private final Context context;
	private CheckBox checkbox;
	private TextView answerContent;
	
	public SurveyAdapter(Context _context,
			List<Answer> _itemList) {
		super(_context, R.layout.single_answer, _itemList);
		context = _context;
		itemList = _itemList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row;
		Answer holder = null;
		LayoutInflater inflater = LayoutInflater.from(getContext());
		row = inflater.inflate(R.layout.single_answer, parent, false);
		holder = getItem(position);
		checkbox = (CheckBox) row.findViewById(R.id.checkboxID);
		checkbox.setChecked(holder.getValue());
		checkbox.setText(holder.getContext());
		row.setTag(holder);
		return row;
	}

	OnCheckedChangeListener checkBoxListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			itemList.get((Integer)buttonView.getTag()).setValue(isChecked);
		}
	};
}
