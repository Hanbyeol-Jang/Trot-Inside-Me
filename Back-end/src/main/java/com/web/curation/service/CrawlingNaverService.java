package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.VideoDto;

public interface CrawlingNaverService {
	public void videoUrl(String url);
	public List<VideoDto> selectVideoList(VideoDto videoDto);
	public void newsUrl(String url);
}
