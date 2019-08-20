package com.regularbbs.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.regularbbs.bbs.dao.UserDao;
import com.regularbbs.bbs.dto.User;
import com.regularbbs.bbs.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public User login(String userId, String password) throws Exception{
		return userDao.findUserByIdAndPw(userId, password);
	}
}
