package com.survey.surveyapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {
	private String context;
	private List<Answer> answers = new ArrayList<Answer>();
	private int id;
	
	public void setId(int pId) {
		id = pId;
	}

	public int getId() {
		return id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}