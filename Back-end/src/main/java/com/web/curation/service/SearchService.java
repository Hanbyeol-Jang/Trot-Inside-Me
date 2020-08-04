package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.SingerDto;

public interface SearchService {

	List<SingerDto> singerAllList();
	SingerDto singerSearch(int s_idx);
	
	public void insertVideo(String s_name); // 네이버 동영상 DB에 넣기
	public void insertArticle(String s_name); // 네이버 기사 DB에 넣기
	public List<BoardDto> selectVideoList(String s_name); // s_name에 대한 전체 동영상 리스트 출력
	public List<BoardDto> selectArticleList(String s_name); // s_name에 대한 전체 기사 리스트 출력
	public boolean isUrl(String vno, String name); // 네이버 동영상 vno가 db에 있는지 확인
	public boolean isUrl2(String url, String name); // 네이버 기사 url이 db에 있는지 확인
	public BoardDto videoDetail(int s_idx, int b_idx); // 가수(s_idx)에 대한 동영상(b_idx)
}
