package com.regularbbs.bbs.dao;

public class UserSqls {
	public static final String GET_USER = "SELECT * from user WHERE userId=:userId AND password=:password";
}
