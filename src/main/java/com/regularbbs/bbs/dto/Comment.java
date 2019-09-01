package com.regularbbs.bbs.dto;

import java.util.Date;

public class Comment {
	private int c_code;
	private int b_code;
	private String comment;
	private String writer;
	private Date reg_datetime;
	
	public int getC_code() {
		return c_code;
	}
	public void setC_code(int c_code) {
		this.c_code = c_code;
	}
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getReg_datetime() {
		return reg_datetime;
	}
	public void setReg_datetime(Date reg_datetime) {
		this.reg_datetime = reg_datetime;
	}
	
	
}
