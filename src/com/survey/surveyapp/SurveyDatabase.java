package com.survey.surveyapp;

import android.database.sqlite.SQLiteOpenHelper;

public class SurveyDatabase extends SQLiteOpenHelper {

	static final String dbName="Survey";
	
	static final String tableTopic="Temat";
	static final String colTopId="id_tem";
	static final String colTopCon="nazwa";
	
	static final String tableQuestion="Pytanie";
	static final String colQueId="id_pyt";
	static final String colQueTop="id_tem";
	static final String colQueCon="tresc";
	
	static final String tableAnswer="Odpowiedz";
	static final String colAnsId="id_odp";
	static final String colAnsQue="id_pyt";
	static final String colAnsTop="id_tem";
	static final String colAnsCon="tresc";
	
	static final String tableTopicResult="RezultatTemat";
	static final String colTopResId="id_rez_tem";
	static final String colTopResTop="id_tem";
	
	static final String tableQuestionResult="RezultatPytanie";
	static final String colQueResId="id_rez_pyt";
	static final String colQueResTopRes="id_rez_tem";
	static final String colQueResQue="id_pyt";
	
	static final String tableAnswerResult="RezultatOdpowiedz";
	static final String colAnsResId="id_rez_odp";
	static final String colAnsResTopRes="id_rez_tem";
	static final String colAnsResQueRes="id_rez_pyt";
	static final String colAnsResAns="id_odp";
	static final String colAnsResRes="wynik";
	
}
