package com.web.curation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.ReplyDto;
import com.web.curation.dto.SingerDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession sqlSession;

	String ns = "com.web.curation.dao.BoardDao.";

	@Override
	public List<SingerDto> singerAllList() {
		return sqlSession.selectList(ns + "singerAllList");
	}

	@Override
	public SingerDto singerDetail(GoodDto dto) {
		return sqlSession.selectOne(ns + "singerDetail", dto);
	}

	@Override
	public SingerDto singerSearch(int s_idx) {
		return sqlSession.selectOne(ns + "singerSearch", s_idx);
	}

	@Override
	public List<BoardPK> mainlist(int b_type) {
		return sqlSession.selectList(ns + "mainlist", b_type);
	}

	@Override
	public void insertVideo(BoardDto boardDto) {
		sqlSession.insert(ns + "addvideo", boardDto);
	}

	@Override
	public void insertArticle(BoardDto boardDto) {
		sqlSession.insert(ns + "addarticle", boardDto);
	}

	@Override
	public List<BoardPK> selectVideoList(String s_name) {
		return sqlSession.selectList(ns + "selectvideolist", s_name);
	}

	@Override
	public boolean isUrl(String vno, String name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("vno", vno);
		map.put("name", name);
		return sqlSession.selectOne(ns + "isurl", map);
	}

	@Override
	public List<BoardPK> selectArticleList(String s_name) {
		return sqlSession.selectList(ns + "selectarticlelist", s_name);
	}

	@Override
	public boolean isUrl2(String url, String name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", url);
		map.put("name", name);
		return sqlSession.selectOne(ns + "isurl2", map);
	}

	@Override
	public BoardPK detail(GoodDto dto) {
		return sqlSession.selectOne(ns + "detail", dto);
	}

	@Override
	public List<ReplyDto> replylist(GoodDto dto) {
		return sqlSession.selectList(ns + "replylist", dto);
	}

	@Override
	public int addComment(ReplyDto replyDto) {
		return sqlSession.insert(ns + "addcomment", replyDto);
	}

	@Override
	public int deleteComment(GoodDto replyDto) {
		return sqlSession.insert(ns + "deletecomment", replyDto);
	}

	@Override
	public int goodClick(GoodDto dto) {
		return sqlSession.insert(ns + "goodClick", dto);
	}

	@Override
	public int goodClickCancel(GoodDto dto) {
		return sqlSession.delete(ns + "goodClickCancel", dto);
	}

	@Override
	public List<BroadCastingDto> broadCastAllList() {
		return sqlSession.selectList(ns + "broadCastAllList");
	}

	@Override
	public List<BroadCastingDto> singerScheduleList(int s_idx) {
		return sqlSession.selectList(ns + "singerScheduleList", s_idx);
	}

//	@Override
//	public int goodClickCancel(BoardPK boardPK) {
//		return sqlSession.delete(ns + "goodClickCancel", boardPK);
//	}
//
//	@Override
//	public int goodCount(BoardPK boardPK) {
//		return sqlSession.selectOne(ns + "goodCount", boardPK);
//	}

//	@Override
//	public int commentCount(BoardPK boardPK) {
//		return sqlSession.selectOne(ns + "commentcount", boardPK);
//	}

}
