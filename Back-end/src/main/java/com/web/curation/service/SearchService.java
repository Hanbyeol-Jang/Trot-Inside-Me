package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.SingerDto;

public interface SearchService {

	List<SingerDto> singerAllList();
	SingerDto singerSearch(int s_idx);
	
}
