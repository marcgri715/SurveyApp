package com.survey.surveyapp;

public class DatabaseManager {
	
	private static DatabaseManager instance = null;
	
	private DatabaseManager() {
	}
	
	public static DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	public void addNewQuestion() {
		addQuestionAnswers();
	}
	
	private void addQuestionAnswers() {
		
	}

	public void addNewSurveyTopic() {
		
	}
	
	
}
