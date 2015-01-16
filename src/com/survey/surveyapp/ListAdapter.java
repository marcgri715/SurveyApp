package com.survey.surveyapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

public class ListAdapter extends ArrayAdapter<String> {

	public List<String> topicList;
	private final Context context;
	private RadioButton lastSelectedRB;
	private int lastSelectedRBIndex;
	
	public ListAdapter(Context _context, List<String> _topicList) {
		super(_context, R.layout.topic_item, _topicList);
		context = _context;
		topicList = _topicList;
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.topic_item, parent, false);
		View temp = view.findViewById(R.id.topic_radio);
		RadioButton radioButton = (RadioButton) view.findViewById(R.id.topic_radio);
		String selectedButtonName = getItem(position);
		radioButton.setText(selectedButtonName);
		radioButton.setTag(position+1);
		radioButton.setOnClickListener(new RadioButton.OnClickListener() {

			@Override
			public void onClick(View _view) {
				if (lastSelectedRBIndex != position && lastSelectedRB!=null)
					lastSelectedRB.setChecked(false);
				lastSelectedRB = (RadioButton)_view;
				lastSelectedRBIndex = position;
			}
		});
						

		return view;

	}	
}
