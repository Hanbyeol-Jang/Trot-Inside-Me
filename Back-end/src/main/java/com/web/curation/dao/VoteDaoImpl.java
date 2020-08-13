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
		return session.insert(ns + "voteClick", voteDto);
	}

}