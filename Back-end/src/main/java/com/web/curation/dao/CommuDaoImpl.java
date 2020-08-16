package com.web.curation.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReply;

@Repository
public class CommuDaoImpl implements CommuDao {

	@Autowired
	SqlSession session;
	
	String ns="com.web.curation.dao.CommuDao.";
	
	@Override
	public List<CommuDto> getCommuList(HashMap<String, String> map) {
		return session.selectList(ns+"getCommuList",map);
	}

	@Override
	public int addCommu(CommuDto dto) {
		return session.insert(ns+"addCommu", dto);
	}

	@Override
	public int deleteCommu(int co_idx) {
		return session.delete(ns+"deleteCommu", co_idx);
	}

	@Override
	public int updateDetail(CommuDto dto) {
		return session.update(ns+"updateCommu", dto);
	}

	@Override
	public CommuDto getCommuDetail(CoGoodDto gdto) {
		return session.selectOne(ns+"getCommuDetail", gdto);
	}

	@Override
	public List<CommuReply> getDetailReplyList(int co_idx) {
		return session.selectList(ns+"getDetailReplyList", co_idx);
	}
	
	@Override
	public int addCommuReply(CoReplyDto dto) {
		return session.insert(ns+"addCommuReply", dto);
	}

	@Override
	public int deleteCommuReply(CoReplyDto dto) {
		return session.delete(ns+"deleteCommuReply", dto);
	}

	@Override
	public int clickGood(CoGoodDto dto) {
		return session.insert(ns+"clickGood",dto);
	}

	@Override
	public int clickGoodCancel(CoGoodDto dto) {
		return session.delete(ns+"clickGoodCancel",dto);
	}

	@Override
	public int deleteDetail(int co_idx) {
		return session.delete(ns+"deleteDetail", co_idx);
	}

	@Override
	public String getImgUrl(int co_idx) {
		return session.selectOne(ns+"getimgurl",co_idx);
	}


}
