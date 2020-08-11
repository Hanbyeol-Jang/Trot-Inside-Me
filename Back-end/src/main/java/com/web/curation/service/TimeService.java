package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;

public interface TimeService {

	void deleteYesterDaySchedule();
	void insertTodaySchedule(ArrayList<BroadCastingDto> list);
	List<SingerDto> selectSinger();
	void deleteSingerSchedule();
	void insertSingerSchedule(List<BroadCastingDto> slist);
	BroadCastingDto selectBroadCasting(int bc_idx); // 인덱스 하나를 받아 로우 조회
	
	
}
