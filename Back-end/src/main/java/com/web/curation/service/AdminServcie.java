package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.AdminDto;

public interface AdminServcie {

	boolean addBroadSchedule(AdminDto dto);
	boolean deleteBroadSchedule(int a_idx);
	List<AdminDto> getBroadScheduleList();

}
