package com.regularbbs.bbs.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.regularbbs.bbs.dao.UserDao;
import com.regularbbs.bbs.dto.User;
import com.regularbbs.bbs.service.UserService;;

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
	public User idCheck(String userId) throws Exception {
		return userDao.getUserById(userId);
	} 
	
	//회원가입
	@Override
	@Transactional(readOnly=false)
	public User insertMember(User user) throws Exception {
		user.setRegdate(new Date());
		userDao.insertMemberInfo(user);
		return user;
	}
	
	//이메일 존재 여부 확인
	@Override
	@Transactional
	public int emailCheck(String email) throws Exception {
		int result = userDao.checkEmail(email);
		System.out.println(result);
		return result;
	}
	
	@Override
	public User getUserInfo(String userId) throws Exception {
		return userDao.getUserById(userId);
	}
	
	//패스워드 일치 확인
	public User checkPw(String userId, String password) throws Exception{
		return userDao.findUserByIdAndPw(userId, password);
	}
	
	//회원 탈퇴
	public int deleteUser(String userId, String password) throws Exception{
		return userDao.deleteUser(userId, password);
	}
	
}
