package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.TimeDao;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;

@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	TimeDao dao;


	@Override
	public void deleteYesterDaySchedule() {
		dao.deleteYesterDaySchedule();
	}

	@Override
	public void insertTodaySchedule(ArrayList<BroadCastingDto> list) {
		dao.insertTodaySchedule(list);
	}

	@Override
	public List<SingerDto> selectSinger() {
		return dao.selectSinger();
	}

	@Override
	public void deleteSingerSchedule() {
		dao.deleteSingerSchedule();
	
	}

	@Override
	public void insertSingerSchedule(List<BroadCastingDto> slist) {
		dao.insertSingerSchedule(slist);
	}

	@Override
	public BroadCastingDto selectBroadCasting(int bc_idx) {
		
		return dao.selectBoradCasting(bc_idx);
	}

	@Override
	public String GetImgByIdx(int a_idx) {
		return dao.getImgByIdx(a_idx);
	}

}
