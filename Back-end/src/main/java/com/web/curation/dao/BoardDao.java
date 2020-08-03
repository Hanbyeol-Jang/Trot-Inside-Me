package com.web.curation.dao;

import java.util.HashMap;
import java.util.List;

import com.google.common.base.Optional;
import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.VideoDto;

public interface BoardDao {

	int followApply(FollowDto dto);
	int followDelete(String userid);
	int goodClick(int boardIdx);
	int goodClickCancel(int b_idx);
	int goodCount(int b_idx);
	int goodAuserConnect(HashMap<String, Object> map);
	int goodAuserConnectDelete(HashMap<String, Object> map);
	List<BroadCastingDto> broadCastAllList();
	List<ArticleDto> articleAllList();
	List<VideoDto> videoAllList();
	List<ArticleDto> articleSearchList(String member);
	List<VideoDto> videoSearchList(String member);
	String searchSingerName(int s_idx);

}
