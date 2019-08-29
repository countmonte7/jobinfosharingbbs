package com.regularbbs.bbs.dao;

import java.util.Date;

public class UserSqls {
	public static final String GET_USER_BY_IDANDPW = "SELECT * from user WHERE userId=:userId AND password=:password";
	public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE userId=:userId";
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email=:email";
	public static final String DELETE_USER = "DELETE FROM user WHERE userId=:userId";
}
