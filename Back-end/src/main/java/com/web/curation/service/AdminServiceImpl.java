package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.AdminDao;
import com.web.curation.dto.AdminDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.dto.UserDto;

@Service
public class AdminServiceImpl implements AdminServcie {

	@Autowired
	AdminDao admindao;

	@Override
	public boolean addBroadSchedule(AdminDto dto) {
		return admindao.addBroadSchedule(dto) == 1;
	}

	@Override
	public boolean deleteBroadSchedule(int a_idx) {
		return admindao.deleteBroadSchedule(a_idx) == 1;
	}

	@Override
	public List<AdminDto> getBroadScheduleList() {
		return admindao.getBroadScheduleList();
	}

	@Override
	public boolean addSinger(SingerDto dto) {
		return admindao.addSinger(dto) == 1;
	}

	@Override
	public boolean deleteSinger(int s_idx) {
		return admindao.deleteSinger(s_idx) == 1;
	}

	@Override
	public List<SingerDto> getSingerList() {
		return admindao.getSingerList();
	}

	@Override
	public String getTVImgUrl(int a_idx) {
		return admindao.getTVImgUrl(a_idx);
	}

	@Override
	public String getSingerImgUrl(int s_idx) {
		return admindao.getSingerImgUrl(s_idx);
	}

	@Override
	public UserDto adminLogin(UserDto userDto) {
		return admindao.adminLogin(userDto);
	}

}
