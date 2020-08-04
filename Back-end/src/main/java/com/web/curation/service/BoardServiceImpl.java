package com.web.curation.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.BoardDao;
import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.VideoDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	@Override
	public boolean followApply(FollowDto dto) {
		return dao.followApply(dto) == 1;
	}

	@Override
	public boolean followDelete(String userid) {
		return dao.followDelete(userid) == 1;
	}

	@Override
	public boolean goodClick(int boardIdx) {
		return dao.goodClick(boardIdx) == 1;
	}

	@Override
	public int goodCount(BoardPK boardPK) {
		return dao.goodCount(boardPK);
	}

	@Override
	public boolean goodAuserConnect(int b_idx, String userEmail) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_idx", b_idx);
		map.put("userEmail", userEmail);
		return dao.goodAuserConnect(map) == 1;
	}

	@Override
	public boolean goodClickCancel(int b_idx) {
		return dao.goodClickCancel(b_idx) == 1;
	}

	@Override
	public boolean goodAuserConnectDelete(int b_idx, String userEmail) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_idx", b_idx);
		map.put("userEmail", userEmail);
		return dao.goodAuserConnectDelete(map) == 1;
	}

	@Override
	public List<BroadCastingDto> broadCastAllList() {
		return dao.broadCastAllList();
	}

	@Override
	public List<ArticleDto> articleAllList() {
		return dao.articleAllList();
	}

	@Override
	public List<VideoDto> videoAllList() {
		return dao.videoAllList();
	}

	@Override
	public List<ArticleDto> articleSearchList(int s_idx) {
		String name = dao.searchSingerName(s_idx);
		return dao.articleSearchList(name);
	}

	@Override
	public List<VideoDto> videoSearchList(int s_idx) {
		String name = dao.searchSingerName(s_idx);
		return dao.videoSearchList(name);
	}

}
