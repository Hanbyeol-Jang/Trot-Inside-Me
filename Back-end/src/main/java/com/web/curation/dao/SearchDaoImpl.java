package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.SingerDto;

@Repository
public class SearchDaoImpl implements SearchDao {
	@Autowired
	SqlSession session;
	String ns = "com.web.curation.dao.SearchDao.";
	@Override
	public List<SingerDto> singerAllList() {
		return session.selectList(ns+"singerAllList");
	}
	@Override
	public SingerDto singerSearch(int s_idx) {
		return session.selectOne(ns+"singerSearch",s_idx);
	}
	
}
