package com.web.curation.dao;

import java.util.ArrayList;
import java.util.List;

import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;

public interface TimeDao {

	void deleteYesterDaySchedule();
	void insertTodaySchedule(ArrayList<BroadCastingDto> list);
	List<SingerDto> selectSinger();
	void deleteSingerSchedule();
	void insertSingerSchedule(List<BroadCastingDto> slist);
	BroadCastingDto selectBoradCasting(int bc_idx);
	String getImgByIdx(int a_idx);
	
}
