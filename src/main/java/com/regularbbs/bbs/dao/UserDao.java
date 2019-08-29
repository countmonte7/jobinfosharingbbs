package com.regularbbs.bbs.dao;

import static com.regularbbs.bbs.dao.UserSqls.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.regularbbs.bbs.dto.User;

@Repository
public class UserDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	private SimpleJdbcInsert insertAction;
	
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("user");
	}
	
	public User findUserByIdAndPw(String userId, String password) {
		if(password==null || password.equals("")) {
			return null;
		}
		try {
			Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("password", password);
			User resultUser = jdbc.queryForObject(GET_USER_BY_IDANDPW, map, rowMapper);
			if(resultUser!=null) {
				return resultUser;
			}
		}catch(DataAccessException e) {
			if(e instanceof IncorrectResultSizeDataAccessException) {
				return null;
			}else {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public User getUserById(String userId){
		User user = null;
		try {
			Map<String, ?> param = Collections.singletonMap("userId", userId);
			user = jdbc.queryForObject(GET_USER_BY_ID, param, rowMapper);
		}catch(EmptyResultDataAccessException e) {
			if(e instanceof IncorrectResultSizeDataAccessException) {
				return user;
			}
		}
		return user;
	}
	
	public int insertMemberInfo(User user) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		int result = insertAction.execute(params);
		return result;
	}
	
	public int checkEmail(String email) {
		int result = 0;
		try {
			Map<String, ?> map = Collections.singletonMap("email", email);
			boolean emailExist = jdbc.query(GET_USER_BY_EMAIL, map, new ResultSetExtractor<Boolean>() {
				@Override
				public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
					return rs.next() ? true : false;
				}
			});
			if(!emailExist) {
				result = 1;
				System.out.println("이메일 사용가능");
			}
			return result;
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return result;
		}
	}
	
	public int deleteUser(String userId, String password) {
		int result = 0;
		try {
			Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("password", password);
			result = jdbc.update(DELETE_USER, map);
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
}
