package com.regularbbs.bbs.dao;

import static com.regularbbs.bbs.dao.UserSqls.GET_USER_BY_EMAIL;
import static com.regularbbs.bbs.dao.UserSqls.GET_USER_BY_ID;
import static com.regularbbs.bbs.dao.UserSqls.GET_USER_BY_IDANDPW;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
	
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("user");
	}
	
	public User findUserByIdAndPw(String userId, String password) {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("password", password);
			return jdbc.queryForObject(GET_USER_BY_IDANDPW, map, rowMapper);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getUserById(String userId){
		int result = 0;
		try {
			Map<String, ?> param = Collections.singletonMap("userId", userId);
			boolean idExistence = jdbc.query(GET_USER_BY_ID, param, new ResultSetExtractor<Boolean>() {
				@Override
				public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
					return rs.next() ? true : false;
				}
			});
			if(!idExistence) {
				result = 1;
			}
			return result;
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return result;
		}
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
}
