package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.FollowDto;
import com.web.curation.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession sqlSession;
	String ns = "com.web.curation.dao.UserDao.";


	@Override
	public UserDto edit(UserDto userDto) throws SQLException {
		return sqlSession.selectOne("com.web.curation.UserDao.edit", userDto);
	}

	@Override
	public void delete(UserDto userDto) throws SQLException {
		sqlSession.delete("com.web.curation.UserDao.delete", userDto);
	}

	@Override
	public String findPassword(String email) {
		return sqlSession.selectOne(ns+".findpassword", email);
	}

	@Override
	public void changePassword(UserDto userDto) {
		sqlSession.update(ns+"changepassword", userDto);
	}

	@Override
	public int join(UserDto user) {
		return sqlSession.insert(ns+"join", user);
	}

	@Override
	public UserDto getUserInfoSuc(String email) {
		return sqlSession.selectOne(ns+"getUserInfoSuc", email);
	}

	@Override
	public List<FollowDto> getFollowList(String userEmail) {
		return sqlSession.selectList(ns+"getFollowList", userEmail);
	}
	
	
}
