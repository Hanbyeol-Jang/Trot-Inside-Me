package com.web.curation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.UserDto;

@Repository
public class KakaoDaoImpl implements KakaoDao {

	@Autowired
	private SqlSession sqlSession;
	String ns = "com.web.curation.dao.KakaoDao";

	@Override
	public Boolean isEmail(String email) {
		return sqlSession.selectOne(ns + ".isemail", email);
	}

	@Override
	public void insertKakao(UserDto userDto) {
		sqlSession.insert(ns + ".insertkakao", userDto);
	}

	@Override
	public void updateKakao(UserDto userDto) {
		sqlSession.update(ns + ".updatekakao", userDto);

	}

	@Override
	public void deleteKakao(String email) {
		sqlSession.delete(ns + ".deletekakao", email);
	}

}
