package com.regularbbs.bbs.dto;

import java.util.Date;

public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private Date regdate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userId, String password, String name, String email, Date regdate) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.regdate = regdate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", regdate=" + regdate + "]";
	}
	
	
	
}
