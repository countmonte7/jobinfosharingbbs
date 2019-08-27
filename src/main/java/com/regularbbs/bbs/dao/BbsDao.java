package com.regularbbs.bbs.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.regularbbs.bbs.dto.Bbs;

import static com.regularbbs.bbs.dao.BbsSqls.*;

@Repository
public class BbsDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Bbs> rowMapper = BeanPropertyRowMapper.newInstance(Bbs.class);
	
	public BbsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("bbs").usingGeneratedKeyColumns("id");
	}
	//글 다 가져오기
	public List<Bbs> selectAll(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PAGING, params, rowMapper);
	}
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
	//글 번호로 글 가져오기
	public Bbs selectById(Integer id) {
		Bbs bbs = null;
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		try {
			bbs = jdbc.queryForObject(SELECT_BY_BBSID, params, rowMapper);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bbs;
	}
	//글 삽입
	public int insert(Bbs bbs) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(bbs);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	//글 삭제
	public int deleteBbs(Integer id) {
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbc.update(DELETE_BY_BBSID, params);
	}
	//글 수정
	public int update(Bbs bbs) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", bbs.getId());
		params.put("title", bbs.getTitle());
		params.put("content", bbs.getContent());
		params.put("regdate", bbs.getRegdate());
		params.put("count", bbs.getCount());
		return jdbc.update(UPDATE_BY_BBSID, params);
	}
}
