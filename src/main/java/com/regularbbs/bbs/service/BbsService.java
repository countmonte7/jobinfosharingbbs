package com.regularbbs.bbs.service;

import java.util.List;

import com.regularbbs.bbs.dto.Bbs;
import com.regularbbs.bbs.dto.Comment;


public interface BbsService {
	public static final Integer LIMIT = 10;
	public static final Integer COMMENT_LIMIT = 20;
	public List<Bbs> getBbs(Integer start);
	public int getCount();
	public Bbs writeBbs(Bbs bbs, String ip);
	public Bbs getBbsById(Integer id);
	public int deleteBbs(Integer id);
	public int updateBbs(Bbs bbs, String ip);
	public Comment insertComment(Comment comment);
	public List<Comment> getComments(Integer start, Integer b_code);
	public int getCommentCount(Integer b_code);
}
