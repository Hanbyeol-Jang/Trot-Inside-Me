package com.web.curation.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.VoteDao;
import com.web.curation.dto.TopDto;
import com.web.curation.dto.VoteDto;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	VoteDao voteDao;

	@Override
	public boolean voteClick(VoteDto voteDto) {
		return voteDao.voteClick(voteDto) == 1;
	}

	@Override
	public boolean voteCheck(String u_email) {
		int temp = voteDao.voteCheck(u_email);

		if (temp == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String idxToName(int s_idx) {
		String temp = voteDao.idxToName(s_idx);
		return temp;
	}

	@Override
	public int thisWeek(int s_idx) {
		int temp = voteDao.thisWeek(s_idx);
		return temp;
	}

	@Override
	public int thisWeekTotal(int s_idx) {
		int temp = voteDao.thisWeekTotal(s_idx);
		return temp;
	}

	@Override
	public int lastWeek(String s_name) {
		int temp = voteDao.lastWeek(s_name);
		return temp;
	}

	@Override
	public int lastWeekTotal(String s_name) {
		int temp = voteDao.lastWeekTotal(s_name);
		return temp;
	}

	@Override
	public int thisWeekRank(int s_idx) {
		int temp = voteDao.thisWeekRank(s_idx);
		return temp;
	}

	@Override
	public int lastWeekRank(String s_name) {
		int temp = voteDao.lastWeekRank(s_name);
		return temp;
	}

	@Override
	public List<TopDto> thisWeekTopThree() {
		return voteDao.thisWeekTopThree();

	}

	@Override
	public List<TopDto> lastWeekTopThree() {
		return voteDao.lastWeekTopThree();
	}
}
