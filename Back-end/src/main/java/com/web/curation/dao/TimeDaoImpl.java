package com.web.curation.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;

@Repository
public class TimeDaoImpl implements TimeDao {

	@Autowired
	private SqlSession session;
	String ns = "com.web.curation.dao.TimeDao.";

	@Override
	public void deleteYesterDaySchedule() {
		session.delete(ns+"deleteYesterDaySchedule");
	}
	@Override
	public void insertTodaySchedule(ArrayList<BroadCastingDto> list) {
		for (BroadCastingDto timeDto : list) {
			session.insert(ns+"insertTodaySchedule", timeDto);
		}
	}
	@Override
	public List<SingerDto> selectSinger() {
		return session.selectList(ns+"selectSinger");
	}
	@Override
	public void deleteSingerSchedule() {
		//bc_member가 널이 아니면
		session.delete(ns+"deleteSingerSchedule");
	}
	@Override
	public void insertSingerSchedule(List<BroadCastingDto> slist) {
		for (BroadCastingDto timeDto : slist) {
			session.insert(ns+"insertSingerSchedule", timeDto);
		}
	}



}
