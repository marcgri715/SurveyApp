package com.survey.surveyapp;

import java.util.List;

public class QuestionObject {
	public String content;
	public String[] tableOfAnswers;
	
	// tre��_pytania, tre�ci_odpowiedzi
	public QuestionObject(String content, String[] answers) {
		this.content = content;
		this.tableOfAnswers = answers;
	}
}
