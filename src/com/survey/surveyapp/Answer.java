package com.survey.surveyapp;

import java.io.Serializable;

public class Answer implements Serializable {
	private String content;
	private boolean value;
	private int id;
	
	public void setId(int pId) {
		id = pId;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
