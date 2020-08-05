package com.web.curation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.KakaoDto;

@Repository
public class KakaoDaoImpl implements KakaoDao {
	
	@Autowired
	private SqlSession sqlSession;
	String ns = "com.web.curation.dao.KakaoDao";
	@Override
	public Boolean isId(String id) {
		return sqlSession.selectOne(ns+".isid",id);
	}
	@Override
	public void insertKakao(KakaoDto kakaoDto) {
		sqlSession.insert(ns+".insertkakao", kakaoDto);
	}

}
