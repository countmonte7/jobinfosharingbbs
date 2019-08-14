package com.regularbbs.bbs.service;

import java.util.List;

import com.regularbbs.bbs.dto.Bbs;


public interface BbsService {
	public static final Integer LIMIT = 10;
	public List<Bbs> getBbs(Integer start);
	public int getCount();
	public Bbs writeBbs(Bbs bbs, String ip);
	public Bbs getBbsById(Integer id);
	public int deleteBbs(Integer id);
	public int updateBbs(Bbs bbs, String ip);
}
