package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.AdminDao;
import com.web.curation.dto.AdminDto;

@Service
public class AdminServiceImpl implements AdminServcie {

	@Autowired
	AdminDao admindao;
	
	@Override
	public boolean addBroadSchedule(AdminDto dto) {
		return admindao.addBroadSchedule(dto)==1;
	}

	@Override
	public boolean deleteBroadSchedule(int a_idx) {
		return admindao.deleteBroadSchedule(a_idx)==1;
	}

	@Override
	public List<AdminDto> getBroadScheduleList() {
		return admindao.getBroadScheduleList();
	}

}
