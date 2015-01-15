package com.survey.surveyapp;

import java.util.ArrayList;

import java.util.List;

public class Result {
	private static Result instance = null;
	private List<Question> questions = new ArrayList<Question>();

	public int getNumberOfQuestions() {
		return questions.size();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public Question getQuestion(int index) {
		return questions.get(index);
	}


	private Result() {
	}

	public static Result getInstance() {
		if (instance == null) {
			instance = new Result();
		}
		return instance;
	}
}