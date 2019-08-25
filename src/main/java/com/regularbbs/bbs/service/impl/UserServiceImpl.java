package com.regularbbs.bbs.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.regularbbs.bbs.dao.UserDao;
import com.regularbbs.bbs.dto.User;
import com.regularbbs.bbs.service.UserService;

import org.springframework.jdbc.core.simple.*;;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public User login(String userId, String password) throws Exception{
		return userDao.findUserByIdAndPw(userId, password);
	}
	
	@Override
	@Transactional
	public int idCheck(String userId) throws Exception {
		return userDao.getUserById(userId);
	} 
	
	@Override
	@Transactional(readOnly=false)
	public User insertMember(User user) throws Exception {
		user.setRegdate(new Date());
		userDao.insertMemberInfo(user);
		return user;
	}
	
	@Override
	@Transactional
	public int emailCheck(String email) throws Exception {
		int result = userDao.checkEmail(email);
		System.out.println(result);
		return result;
	}
}
