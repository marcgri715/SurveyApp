package com.survey.surveyapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AnswerManager {
	
	private SQLiteDatabase db;
	
	public void addNewAnswer(Result result, Question question, Answer answer) {
		
		int numberOfAnswers;
		int numberOfQuestions = result.getQuestions().size();
		
		for (int i = 0; i < numberOfQuestions; i++) {
			numberOfAnswers = result.getQuestion(i).getAnswers().size();
			for (int j = 0; j < numberOfAnswers; j++) {
				insertAnswer(answer, result.getQuestion(i));
			}
		}		
	}
	
	public void insertAnswer(Answer answer, Question question) {
		ContentValues cv = new ContentValues();
		cv.put("ID_Odp", answer.getId());
		cv.put("ID_RezPyt", question.getId());
		cv.put("Wynik", answer.getValue());
		db.insert("RezOdpowiedz", null, cv);		
	}	
}
