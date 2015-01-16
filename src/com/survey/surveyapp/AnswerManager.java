package com.survey.surveyapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AnswerManager {
	
	private SQLiteDatabase db;
	
	public void addNewAnswer(Answer answer, Result result, int questionIndex) {
		
		int numberOfAnswers;
		numberOfAnswers = result.getInstance().getQuestion(questionIndex).getAnswers().size();
		
		for (int j = 0; j < numberOfAnswers; j++) {
			insertAnswer(answer.getId(), answer.getValue(), result.getQuestion(questionIndex).getId());
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
	
	public void insertKeys(Answer answer, Question question) {
		ContentValues cv = new ContentValues();
		int topicId = Result.getInstance().getId();	
		int questionId = question.getId();
		int answerId = answer.getId();
		
		cv.put("ID_Tem", topicId);
		topicId = (int) db.insert("RezTemat", null, cv);
		cv.clear();
		
		cv.put("ID_RezTem", topicId);
		db.insert("RezPytanie", null, cv);
		cv.clear();		
		
		for (int i = 0; i < question.getAnswers().size(); i++) {
			cv.put("ID_Pyt", questionId);
			questionId = (int) db.insert("RezQuestion", null, cv);
			
			db.insert("RezPytanie", null, cv);
		}
		
		
		cv.put("ID_Tem", topicId);
		db.insert("RezTemat", null, cv);
		cv.clear();		
	}	
}
