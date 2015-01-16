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
		db.close();
		dbHelper.close();
	}
	
	public List<String> getTopics () {
		List<String> topicList = new ArrayList<String>();
		Cursor topicCursor = db.query("Temat", new String[] { "Nazwa" }, null, null, null, null, null);
		if (topicCursor.moveToFirst()) {
			do {
				String topic = topicCursor.getString(0);
				topicList.add(topic);
			} while (topicCursor.moveToNext());
		}
		return topicList;
	}
	
	public void getQuestions(int surveyIndex) {
		List<Question> qlist = new ArrayList<Question>();
		Cursor questionCursor = db.query("Pytanie", null, "ID_Tem=?", new String[] {Integer.toString(surveyIndex)}, null, null, null);
		if (questionCursor.moveToFirst()) {
			do {
				Question q = new Question();
				q.setId(questionCursor.getInt(0));
				q.setContent(questionCursor.getString(2));
				List<Answer> alist = new ArrayList<Answer>();
				int id = q.getId();
				Cursor answerCursor = db.query("Odpowiedz", null, "ID_Pyt=?", new String[] {Integer.toString(id)}, null, null, null);
				if (answerCursor.moveToFirst()) {
					do {
						Answer a = new Answer();
						a.setId(answerCursor.getInt(0));
						a.setContent(answerCursor.getString(2));
						alist.add(a);
					} while (answerCursor.moveToNext());
				}
				q.setAnswers(alist);
				answerCursor.close();
				qlist.add(q);
			} while (questionCursor.moveToNext());
		}
		questionCursor.close();
		Result.getInstance().setQuestions(qlist);
	}
	
	// id_tematu, pytanie z odpowiedziami
	public void addNewQuestion(long topic_id, QuestionObject question) {
		ContentValues cv = new ContentValues();
		cv.put("ID_Tem", topic_id);
		cv.put("Tresc", question.content);
		long question_id = db.insert("Pytanie", null, cv);

		for (String answer : question.tableOfAnswers)
		{
			cv = new ContentValues();
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
		long SurveyTopic1 = addNewSurveyTopic("Samochody");
		long SurveyTopic2 = addNewSurveyTopic("Œlubne preferencje");
		
		// odpowiedzi na pytania
		String[] odp1_1 = { "95", "98", "Diesel", "Gaz" };
		String[] odp1_2 = { "Dojazd do pracy", "To moje narzêdzie pracy", "Wo¿ê rodzinê na zakupy i wycieczki", "¯eby siê pokazaæ", "U¿ywam samochodu sporadycznie" };
		String[] odp1_3 = { "Michelin", "Dêbica", "Je¿d¿ê na felgach" };
		String[] odp1_4 = { "Tak", "Nie", "Nie wiem" };
		
		String[] odp2_1 = { "Jesieñ", "Zima", "Wiosna", "Lato" };
		String[] odp2_2 = { "Plan w³asny", "qwer", "Magazun œlubny", "Rodzina i przyjaciele" };
		String[] odp2_3 = { "0-50", "51-100", "101-200", "151 +" };
		String[] odp2_4 = { "0-5,000€", "5,001-10,000€", "10,001-20,000€", "20,001€-50,000€", "50,001€ +" };
		String[] odp2_5 = { "18-25", "26-35", "36-50", "51-60", "61 +" };
		
		// listy pytañ
		List<QuestionObject> Questions1 = new ArrayList<QuestionObject>();
		Questions1.add(new QuestionObject("Jakie paliwo tankujesz do swojego auta? ", odp1_1));
		Questions1.add(new QuestionObject("Do czego na co dzieñ u¿ywasz swojego samochodu?", odp1_2));
		Questions1.add(new QuestionObject("Opon jakiej firmy u¿ywasz na codzieñ?", odp1_3));
		Questions1.add(new QuestionObject("Czy uwa¿asz, ¿e ta ankieta ma jakikolwiek sens?", odp1_4));
		
		List<QuestionObject> Questions2 = new ArrayList<QuestionObject>();
		Questions2.add(new QuestionObject("O jakiej porze roku planujesz wesele?", odp2_1));
		Questions2.add(new QuestionObject("Jak zamierzasz zaplanowaæ œlub?", odp2_2));
		Questions2.add(new QuestionObject("Ilu goœci oczekujesz?", odp2_3));
		Questions2.add(new QuestionObject("Jaki jest Twój planowany bud¿et weselny?", odp2_4));
		Questions2.add(new QuestionObject("Jaki jest uœredniony wiek pañstwa m³odych?", odp2_5));
		
		// dorzucanie pytañ z odpowiedziami do akniet
		for (QuestionObject QO : Questions1)
			addNewQuestion(SurveyTopic1, QO);
		for (QuestionObject QO : Questions2)
			addNewQuestion(SurveyTopic2, QO);
	}
	
	public void insertKeys() {
		ContentValues cv = new ContentValues();
		long topicId = Result.getInstance().getId();
		
		cv.put("ID_Tem", topicId);
		long resTopicId = db.insert("RezTemat", null, cv);
		cv.clear();
		
		for (int i=0; i<Result.getInstance().getNumberOfQuestions(); i++) {
			long questionId = Result.getInstance().getQuestion(i).getId();
			cv.put("ID_RezTem", resTopicId);
			cv.put("ID_Pyt", questionId);
			long resAnswerId = db.insert("RezPytanie", null, cv);
			cv.clear();
			
			for (int j=0; j<Result.getInstance().getQuestion(i).getNumberOfAnswers(); j++) {
				long answerId = Result.getInstance().getQuestion(i).getAnswer(j).getId();
				int result;
				if (Result.getInstance().getQuestion(i).getAnswer(j).getValue()) {
					result = 1;
				} else {
					result = 0;
				}
				cv.put("ID_RezPyt", resAnswerId);
				cv.put("ID_Odp", answerId);
				cv.put("Wynik", result);
				db.insert("RezOdpowiedz", null, cv);
			}
		}
	}	
}
