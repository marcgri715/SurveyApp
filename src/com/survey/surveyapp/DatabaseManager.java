package com.survey.surveyapp;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
	private SQLiteDatabase db;
	private Database dbHelper;
	private static DatabaseManager instance = null;
	
	private DatabaseManager() {
	//	dbHelper = new Database(this);
	}
	
	public static DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	// id_tematu, pytanie z odpowiedziami
	public void addNewQuestion(Integer topic_id, QuestionObject question) {
		ContentValues cv = new ContentValues();
		cv.put("ID_Tem", topic_id);
		cv.put("Tresc", question.content);
		db.insert("Pytanie", null, cv);

		Cursor c = db.rawQuery("SELECT last_insert_rowid()", null);
		c.moveToFirst();
		int question_id = c.getInt(0);
		question_id++;
		
		for (String answer : question.tableOfAnswers)
		{
			cv.put("ID_Pyt", question_id);
			cv.put("Tresc", answer);
			db.insert("Odpowiedz", null, cv);
		}
		c.close();
	}

	// zwraca id_tematu
	public Integer addNewSurveyTopic(String nazwaTematu) {
		ContentValues cv = new ContentValues();
		cv.put("Nazwa", nazwaTematu);
		db.insert("Temat", null, cv);
		
		Cursor c = db.rawQuery("SELECT last_insert_rowid()", null);
		c.moveToFirst();
		int topic_id = c.getInt(0);
		topic_id++;
		c.close();
		return topic_id;
	}
}
