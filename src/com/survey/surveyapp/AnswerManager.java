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
	
	public void insertAnswer(int answerId, boolean answerValue, int questionId) {
		ContentValues cv = new ContentValues();
		cv.put("ID_Odp", answerId);
		cv.put("ID_RezPyt", questionId);
		cv.put("Wynik", answerValue);
		db.insert("RezOdpowiedz", null, cv);		
	}	
	
	public void insertKeys(int questionId, int topicId, int topicIdRez) {
		ContentValues cv = new ContentValues();
		cv.put("ID_Pyt", questionId);
		cv.put("ID_RezTem", topicIdRez);
		db.insert("RezPytanie", null, cv);
		cv.clear();
		
		cv.put("ID_Tem", topicId);
		db.insert("RezTemat", null, cv);
		cv.clear();		
	}
	
}
