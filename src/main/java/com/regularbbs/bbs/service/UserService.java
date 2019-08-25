package com.regularbbs.bbs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.regularbbs.bbs.dto.User;

public interface UserService {
	public User login(String userId, String password) throws Exception;
	public int idCheck(String userId) throws Exception;
	public User insertMember(User user) throws Exception;
	public int emailCheck(String email) throws Exception;
}
