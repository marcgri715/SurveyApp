package com.survey.surveyapp;

import java.util.List;

public class QuestionObject {
	public String content;
	public String[] tableOfAnswers;
	
	// treœæ_pytania, treœci_odpowiedzi
	public QuestionObject(String content, String[] answers) {
		this.content = content;
		this.tableOfAnswers = answers;
	}
}
