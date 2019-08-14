package com.regularbbs.bbs.dao;

public class BbsSqls {
	public static final String SELECT_PAGING = "SELECT id, writer, title, content, regdate FROM bbs ORDER BY id DESC LIMIT :start, :limit";
	public static final String SELECT_COUNT = "SELECT count(*) FROM bbs";
	public static final String DELETE_BY_BBSID = "DELETE FROM bbs WHERE id = :id";
	public static final String SELECT_BY_BBSID = "SELECT id, writer, title, content, regdate FROM bbs WHERE id = :id";
	public static final String UPDATE_BY_BBSID = "UPDATE bbs SET title=:title, content=:content, regdate=:regdate WHERE id = :id";
}
