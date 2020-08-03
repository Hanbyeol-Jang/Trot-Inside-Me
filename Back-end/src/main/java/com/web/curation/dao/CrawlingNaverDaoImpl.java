package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.VideoDto;

@Repository
public class CrawlingNaverDaoImpl implements CrawlingNaverDao {
	
	@Autowired
	private SqlSession sqlSession;
	String ns = "com.web.curation.dao.CrawlingNaverDao";
	
	@Override
	public void insertVideo(VideoDto videoDto) {
		sqlSession.insert(ns+".addvideo", videoDto);
	}

	@Override
	public void insertArticle(ArticleDto articleDto) {
		sqlSession.insert(ns+".addarticle", articleDto);
	}


	@Override
	public List<VideoDto> selectVideoList(VideoDto videoDto) {
		
		return sqlSession.selectList(ns+".selectvideolist",videoDto);
	}

	
	
}
