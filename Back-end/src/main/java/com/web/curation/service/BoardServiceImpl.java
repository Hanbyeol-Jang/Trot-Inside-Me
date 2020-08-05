package com.web.curation.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.BoardDao;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.FollowDto;

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
	public boolean goodClick(BoardPK boardPK) {
		return dao.goodClick(boardPK) == 1;
	}

	@Override
	public boolean goodClickCancel(BoardPK boardPK) {
		return dao.goodClickCancel(boardPK) == 1;
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

}
