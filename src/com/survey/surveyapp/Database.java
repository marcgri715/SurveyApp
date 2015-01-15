package com.survey.surveyapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

	public Database(Context context){
		super(context, "Survey.db", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
		db.execSQL("CREATE TABLE if not exists Temat(ID_Tem INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "Nazwa TEXT)");
		db.execSQL("Create Table if not exists Pytanie(ID_Pyt integer primary key autoincrement, "
				+ "ID_Tem Integer, "
				+ "Tresc text, "
				+ "foreign key(ID_Tem)REFERENCES Temat(ID_Tem))");
		db.execSQL("CREATE TABLE if not exists Odpowiedz(ID_Odp integer primary key autoincrement, "
				+ "ID_Pyt integer, "
				+ "Tresc text, "
				+ "foreign key(ID_Pyt) references Pytanie(ID_Pyt))");
		db.execSQL("Create table if not exists RezTemat(ID_RezTem integer primary key autoincrement, "
				+ "ID_Tem, "
				+ "foreign key(ID_Tem) references Temat(ID_Tem))");
		db.execSQL("create table if not exists RezPytanie(ID_RezPyt integer primary key autoincrement, "
				+ "ID_RezTem integer, "
				+ "ID_Pyt integer, "
				+ "foreign key(ID_Pyt) references Pytanie(ID_Pyt), "
				+ "foreign key(ID_RezTem) references RezTemat(ID_RezTem))");
		db.execSQL("CREATE TABLE if not exists RezOdpowiedz(ID_RezOdp integer primary key autoincrement, "
				+ "ID_RezPyt integer, "
				+ "ID_Odp integer,"
				+ "Wynik integer, "
				+ "foreign key(ID_Odp) references Odpowiedz(ID_Odp),"
				+ "foreign key(ID_RezPyt) references RezPytanie(ID_RezPyt))");		
		}
		catch(Exception ex){
			String exc = ex.getMessage();
			ex.printStackTrace();
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
