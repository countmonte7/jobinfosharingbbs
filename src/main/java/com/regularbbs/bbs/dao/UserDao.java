package com.regularbbs.bbs.dao;

import static com.regularbbs.bbs.dao.UserSqls.GET_USER;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.regularbbs.bbs.dto.User;

@Repository
public class UserDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public User findUserByIdAndPw(String userId, String password) {
		try {
			System.out.println(userId);
			Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("password", password);
			return jdbc.queryForObject(GET_USER, map, rowMapper);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
