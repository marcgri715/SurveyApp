package com.survey.surveyapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AnswerManager {
	
	private SQLiteDatabase db;
	
	public void addNewAnswer(Answer answer, Result result, int questionIndex) {
		
		int numberOfAnswers;
		numberOfAnswers = result.getInstance().getQuestion(questionIndex).getAnswers().size();
		
		for (int j = 0; j < numberOfAnswers; j++) {
			
		}			
		
	}
	
	public void insertAnswer(Answer answer, Question question) {
		ContentValues cv = new ContentValues();
		
		int topicId = Result.getInstance().getId();	
		int questionId = question.getId();
		int answerId = answer.getId();
	
		cv.put("ID_Odp", answerId);
		cv.put("ID_RezPyt", questionId);
		cv.put("Wynik", answer.getValue());
		db.insert("RezOdpowiedz", null, cv);		
		cv.clear();		
	}
	
	
}
//do skasowania
