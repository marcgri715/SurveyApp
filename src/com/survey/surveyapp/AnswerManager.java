package com.survey.surveyapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AnswerManager {
	
	private SQLiteDatabase db;
	public void addNewAnswer(Result result, Question question) {
		
		int numberOfAnswers;
		int numberOfQuestions = result.getQuestions().size();
		
		for (int i = 0; i < numberOfQuestions; i++) {
			numberOfAnswers = result.getQuestion(i).getAnswers().size();
			for (int j = 0; j < numberOfAnswers; j++) {
				//insert
			}
		}		
	}
	
	public void insertAnswerId(Answer answer) {
		ContentValues cv = new ContentValues();
		//cv.put("id", );
		//db.insert("RezOdpowiedz", nullColumnHack, values)
	}
	
	public void insertQuestionId() {
		
	}
	
	public void insertResultId() {
		
	}
	
	
}
