package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.SingerDto;

public interface SearchDao {

	List<SingerDto> singerAllList();
	SingerDto singerSearch(int s_idx);

}
