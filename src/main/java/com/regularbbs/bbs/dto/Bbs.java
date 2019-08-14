package com.regularbbs.bbs.dto;

import java.util.Date;

public class Bbs {
	private int id;
	private String writer;
	private String title;
	private String content;
	private Date regdate;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Bbs [id=" + id + ", writer=" + writer + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", password=" + password + "]";
	}
	
	
}
