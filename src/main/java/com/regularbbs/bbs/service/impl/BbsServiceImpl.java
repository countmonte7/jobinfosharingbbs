package com.regularbbs.bbs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.regularbbs.bbs.dao.BbsDao;
import com.regularbbs.bbs.dao.LogDao;
import com.regularbbs.bbs.dto.Bbs;
import com.regularbbs.bbs.dto.Comment;
import com.regularbbs.bbs.dto.Log;
import com.regularbbs.bbs.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService{
	@Autowired
	BbsDao bbsDao;
	
	@Autowired
	LogDao logDao;
	
	@Override 
	@Transactional
	public List<Bbs> getBbs(Integer start) {
		List<Bbs> list = bbsDao.selectAll(start, BbsService.LIMIT);
		return list;
	}
	@Override
	public int getCount() {
		return bbsDao.selectCount();
	}
	
	@Override
	@Transactional
	public Bbs getBbsById(Integer id) {
		return bbsDao.selectById(id);
	}
	
	@Override
	@Transactional(readOnly=false)
	public Bbs writeBbs(Bbs bbs, String ip) {
		bbs.setRegdate(new Date());
		int id = bbsDao.insert(bbs);
		bbs.setId(id);
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("write");
		log.setRegdate(new Date());
		logDao.insert(log);
		return bbs;
	}
	
	@Override
	public int deleteBbs(Integer id) {
		int result = bbsDao.deleteBbs(id);
		return result;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int updateBbs(Bbs bbs, String ip) {
		bbs.setRegdate(new Date());
		int result = bbsDao.update(bbs);
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("update");
		log.setRegdate(new Date());
		logDao.insert(log);
		return result;
	}
	
	//댓글 등록하기
	@Override
	@Transactional(readOnly=false)
	public Comment insertComment(Comment comment) {
		comment.setReg_datetime(new Date());
		int cCode = bbsDao.insertComment(comment);
		comment.setC_code(cCode);
		return comment;
	}
	
	//댓글 리스트 가져오기
	@Override
	@Transactional
	public List<Comment> getComments(Integer start, Integer b_code) {
		List<Comment> comments = bbsDao.selectLists(start, BbsService.COMMENT_LIMIT, b_code);
		return comments;
	}
	
	@Override
	public int getCommentCount(Integer bbsId) {
		return bbsDao.selectCommentCount(bbsId);
	}
}
