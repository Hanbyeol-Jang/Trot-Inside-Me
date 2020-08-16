package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.AdminDto;
import com.web.curation.dto.SingerDto;

public interface AdminServcie {

	boolean addBroadSchedule(AdminDto dto);
	boolean deleteBroadSchedule(int a_idx);
	List<AdminDto> getBroadScheduleList();
	boolean addSinger(SingerDto dto);
	boolean deleteSinger(int s_idx);
	List<SingerDto> getSingerList();
	String getTVImgUrl(int a_idx);
	String getSingerImgUrl(int s_idx);

}
