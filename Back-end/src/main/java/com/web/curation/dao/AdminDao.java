package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.AdminDto;
import com.web.curation.dto.SingerDto;

public interface AdminDao {

	int addBroadSchedule(AdminDto dto);
	int deleteBroadSchedule(int a_idx);
	List<AdminDto> getBroadScheduleList();
	int addSinger(SingerDto dto);
	int deleteSinger(int s_idx);
	List<SingerDto> getSingerList();

}
