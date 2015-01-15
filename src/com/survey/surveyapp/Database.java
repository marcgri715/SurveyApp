package com.survey.surveyapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

	public Database(Context context){
		super(context, "Survey", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
		db.execSQL("CREATE TABLE  if not exist Temat(ID_Tem INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "Nazwa TEXT)");
		db.execSQL("Create Table if not exist Pytanie(ID_Pyt integer primary key autoincrement, "
				+ "ID_Tem Integer, "
				+ "Tresc text, "
				+ "foreignkey(ID_Tem)REFERENCES Temat(ID_Tem))");
		db.execSQL("CREATE TABLE if not exist Odpowiedz(ID_Odp integer primary key autoincrement, "
				+ "ID_Pyt integer, "
				+ "Tresc text, "
				+ "foreignkey(ID_Pyt) references Pytanie(ID_Pyt))");
		db.execSQL("Create table if not exist RezTemat(ID_RezTem integer primary key autoincrement, "
				+ "ID_Tem, "
				+ "foreignkey(ID_Tem) references Temat(ID_Tem))");
		db.execSQL("create table if not exist RezPytanie(ID_RezPyt integer primary key autoincrement, "
				+ "ID_RezTem integer, "
				+ "ID_Pyt integer, "
				+ "foreignkey(ID_Pyt) references Pytanie(ID_Pyt), "
				+ "foreignkey(ID_RezTem) references RezTemat(ID_RezTem))");
		db.execSQL("CREATE TABLE if not exist RezOdpowiedz(ID_RezOdp integer primary key autoincrement, "
				+ "ID_RezPyt integer, "
				+ "ID_Odp integer,"
				+ "Wynik integer, "
				+ "foreignkey(ID_Odp) references Odpowiedz(ID_Odp),"
				+ "foreignkey(ID_RezPyt) references RezPytanie(ID_RezPyt))");		
		}
		catch(Exception ex){
			
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
