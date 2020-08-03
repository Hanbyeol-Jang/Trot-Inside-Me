package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.VideoDto;

public interface CrawlingNaverDao {
	public void insertVideo(VideoDto videoDto);
	public void insertArticle(ArticleDto articleDto);
	public List<VideoDto> selectVideoList(VideoDto videoDto);

}

