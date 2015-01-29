package com.survey.surveyapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {
	private String content;
	private List<Answer> answers = new ArrayList<Answer>();
	private int id;
	private boolean answered;
	
	public boolean isAnswered () {
		return answered;
	}
	
	public void setAnsweredFlag (boolean pAns) {
		answered = pAns;
	}
	
	public void setId(int pId) {
		id = pId;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	
	public Answer getAnswer(int index) {
		return answers.get(index);
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public int getNumberOfAnswers() {
		return answers.size();
	}
}