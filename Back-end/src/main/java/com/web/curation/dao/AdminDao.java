package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.AdminDto;

public interface AdminDao {

	int addBroadSchedule(AdminDto dto);
	int deleteBroadSchedule(int a_idx);
	List<AdminDto> getBroadScheduleList();

}
