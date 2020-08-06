package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReplyUser;

@Repository
public class CommuDaoImpl implements CommuDao {

	@Autowired
	SqlSession session;
	
	String ns="com.web.curation.dao.CommuDao.";
	
	@Override
	public List<CommuDto> getCommuList() {
		return session.selectList(ns+"getCommuList");
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
	public List<CommuReplyUser> getCommuDetail(int co_idx) {
		return session.selectList(ns+"getCommuDetail", co_idx);
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

}
