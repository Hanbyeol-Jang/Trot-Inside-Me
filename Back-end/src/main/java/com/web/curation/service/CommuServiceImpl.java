package com.web.curation.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.CommuDao;
import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReply;

@Service
public class CommuServiceImpl implements CommuService {

	@Autowired
	CommuDao commuDao;
	
	@Override
	public List<CommuDto> getCommuList(String u_email,String order) {
		HashMap<String, String> map = new HashMap<>();
		map.put("u_email", u_email);
		map.put("order", order);
		return commuDao.getCommuList(map);
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
	@Override
	public boolean clickGoodCancel(CoGoodDto dto) {
		return commuDao.clickGoodCancel(dto)==1;
	}
	@Override
	public boolean deleteDetail(int co_idx) {
		return commuDao.deleteDetail(co_idx)==1;
	}
	@Override
	public CommuDto getCommuDetail(CoGoodDto gdto) {
		return commuDao.getCommuDetail(gdto);
	}
	@Override
	public List<CommuReply> getDetailReplyList(int co_idx) {
		return commuDao.getDetailReplyList(co_idx);
	}
	
}
