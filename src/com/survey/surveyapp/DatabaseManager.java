package com.survey.surveyapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
	
	public void open(Context context) {
		dbHelper = new Database(context);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLException e) {
			db = dbHelper.getReadableDatabase();
		}
	}
	
	public void close() {
		dbHelper.close();
	}
	
	// id_tematu, pytanie z odpowiedziami
	public void addNewQuestion(long topic_id, QuestionObject question) {
		ContentValues cv = new ContentValues();
		cv.put("ID_Tem", topic_id);
		cv.put("Tresc", question.content);
		long question_id = db.insert("Pytanie", null, cv);

		for (String answer : question.tableOfAnswers)
		{
			cv.put("ID_Pyt", question_id);
			cv.put("Tresc", answer);
			db.insert("Odpowiedz", null, cv);
		}
	}

	// zwraca id_tematu
	public long addNewSurveyTopic(String nazwaTematu) {
		ContentValues cv = new ContentValues();
		cv.put("Nazwa", nazwaTematu);
		return db.insert("Temat", null, cv);
	}
	
	// tutaj dodamy ankietê startow¹
	public void setupStartingQuestions() {
		// tematy ankiet
		long SurveyTopic1 = addNewSurveyTopic("Wiêcej ni¿ jedno zwierzê");
		long SurveyTopic2 = addNewSurveyTopic("akieta2");
		
		// odpowiedzi na pytania
		String[] odp1_1 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp1_2 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp1_3 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp1_4 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp1_5 = { "asdf", "qwer", "zxcv", "qwerty" };
		
		String[] odp2_1 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp2_2 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp2_3 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp2_4 = { "asdf", "qwer", "zxcv", "qwerty" };
		String[] odp2_5 = { "asdf", "qwer", "zxcv", "qwerty" };
		
		// listy pytañ
		List<QuestionObject> Questions1 = new ArrayList<QuestionObject>();
		Questions1.add(new QuestionObject("Treœæ pytania?", odp1_1));
		Questions1.add(new QuestionObject("Treœæ pytania?", odp1_2));
		Questions1.add(new QuestionObject("Treœæ pytania?", odp1_3));
		Questions1.add(new QuestionObject("Treœæ pytania?", odp1_4));
		Questions1.add(new QuestionObject("Treœæ pytania?", odp1_5));
		
		List<QuestionObject> Questions2 = new ArrayList<QuestionObject>();
		Questions2.add(new QuestionObject("Treœæ pytania?", odp2_1));
		Questions2.add(new QuestionObject("Treœæ pytania?", odp2_2));
		Questions2.add(new QuestionObject("Treœæ pytania?", odp2_3));
		Questions2.add(new QuestionObject("Treœæ pytania?", odp2_4));
		Questions2.add(new QuestionObject("Treœæ pytania?", odp2_5));
		
		// dorzucanie pytañ z odpowiedziami do akniet
		for (QuestionObject QO : Questions1)
			addNewQuestion(SurveyTopic1, QO);
		for (QuestionObject QO : Questions2)
			addNewQuestion(SurveyTopic2, QO);
	}
}
