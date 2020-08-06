package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.CommuDao;
import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReplyUser;

@Service
public class CommuServiceImpl implements CommuService {

	@Autowired
	CommuDao commuDao;
	
	@Override
	public List<CommuDto> getCommuList() {
		return commuDao.getCommuList();
	}
	@Override
	public boolean addCommu(CommuDto dto) {
		return commuDao.addCommu(dto)==1;
	}

	@Override
	public boolean deleteCommu(int co_idx) {
		return commuDao.deleteCommu(co_idx)==1;
	}
	@Override
	public List<CommuReplyUser> getCommuDetail(int co_idx) {
		return commuDao.getCommuDetail(co_idx);
	}
	@Override
	public boolean addCommuReply(CoReplyDto dto) {
		return commuDao.addCommuReply(dto)==1;
	}
	@Override
	public boolean deleteCommuReply(CoReplyDto dto) {
		return commuDao.deleteCommuReply(dto)==1;
	}
	@Override
	public boolean clickGood(CoGoodDto dto) {
		return commuDao.clickGood(dto)==1;
	}
	
}
