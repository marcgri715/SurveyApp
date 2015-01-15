package com.survey.surveyapp;

import java.io.Serializable;

public class Answer implements Serializable {
	private String context;
	private boolean value;
	private int id;
	
	public void setId(int pId) {
		id = pId;
	}

	public int getId() {
		return id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
