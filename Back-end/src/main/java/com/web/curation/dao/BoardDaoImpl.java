package com.web.curation.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.VideoDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession session;

	String ns = "com.web.curation.dao.BoardDao.";

	@Override
	public int followApply(FollowDto dto) {
		return session.insert(ns + "followApply", dto);
	}

	@Override
	public int followDelete(String u_email) {
		return session.delete(ns + "followDelete", u_email);
	}

	@Override
	public int goodClick(BoardPK boardPK) {
		return session.update(ns + "goodClick", boardPK);
	}

	@Override
	public int goodClickCancel(BoardPK boardPK) {
		return session.delete(ns + "goodClickCancel", boardPK);
	}

	@Override
	public int goodCount(BoardPK boardPK) {
		return session.selectOne(ns + "goodCount", boardPK);
	}

	@Override
	public int goodAuserConnect(HashMap<String, Object> map) {
		return session.insert(ns + "goodAuserConnect", map);
	}

	@Override
	public int goodAuserConnectDelete(HashMap<String, Object> map) {
		return session.delete(ns + "goodAuserConnectDelete", map);
	}

	@Override
	public List<BroadCastingDto> broadCastAllList() {
		return session.selectList(ns + "broadCastAllList");
	}

	@Override
	public List<ArticleDto> articleAllList() {
		return session.selectList(ns + "articleAllList");
	}

	@Override
	public List<VideoDto> videoAllList() {
		return session.selectList(ns + "videoAllList");
	}

	@Override
	public List<ArticleDto> articleSearchList(String a_member) {
		return session.selectList(ns + "articleSearchList", a_member);
	}

	@Override
	public List<VideoDto> videoSearchList(String v_member) {
		return session.selectList(ns + "videoSearchList", v_member);
	}

	@Override
	public String searchSingerName(int s_idx) {
		return session.selectOne(ns + "searchSingerName", s_idx);
	}

}
