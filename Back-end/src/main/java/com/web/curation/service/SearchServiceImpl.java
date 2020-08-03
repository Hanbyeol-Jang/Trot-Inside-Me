package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.SearchDao;
import com.web.curation.dto.SingerDto;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	SearchDao dao;

	@Override
	public List<SingerDto> singerAllList() {
		return dao.singerAllList();
	}

	@Override
	public SingerDto singerSearch(int s_idx) {
		return dao.singerSearch(s_idx);
	}
	
	
	
}
