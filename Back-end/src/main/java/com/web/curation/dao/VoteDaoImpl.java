package com.web.curation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.VoteDto;

@Repository
public class VoteDaoImpl implements VoteDao {

	@Autowired
	SqlSession session;

	String ns = "com.web.curation.dao.VoteDao.";

	@Override
	public int voteClick(VoteDto voteDto) {
		session.insert(ns + "voteClick", voteDto);
		return session.update(ns + "voteChange", voteDto);
	}

	@Override
	public int voteCheck(String u_email) {
		return session.selectOne(ns + "voteCheck", u_email);
	}

	@Override
	public String idxToName(int s_idx) {
		return session.selectOne(ns + "idxToName", s_idx);
	}

	@Override
	public int thisWeek(int s_idx) {
		return session.selectOne(ns + "thisWeek", s_idx);
	}

	@Override
	public int thisWeekTotal(int s_idx) {
		return session.selectOne(ns + "thisWeekTotal", s_idx);
	}

	@Override
	public int lastWeek(String s_name) {
		return session.selectOne(ns + "lastWeek", s_name);
	}

	@Override
	public int lastWeekTotal(String s_name) {
		return session.selectOne(ns + "lastWeekTotal", s_name);
	}
	
	@Override
	public int thisWeekRank(int s_idx) {
		return session.selectOne(ns + "thisWeekRank", s_idx);
	}
	
	@Override
	public int lastWeekRank(String s_name) {
		return session.selectOne(ns + "lastWeekRank", s_name);
	}

}
