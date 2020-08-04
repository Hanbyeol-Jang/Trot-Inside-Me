package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.VideoDto;

public interface BoardService {

	boolean followApply(FollowDto dto);

	boolean followDelete(String string);

	/* 좋아요 클릭 */
	boolean goodClick(BoardPK boardPK);
	
	/* 좋아요 취소 */
	boolean goodClickCancel(BoardPK boardPK);

	/* 좋아요 수 표시 */
	int goodCount(BoardPK boardPK);

	boolean goodAuserConnect(int b_idx, String userEmail);



	boolean goodAuserConnectDelete(int b_idx, String userEmail);

	List<BroadCastingDto> broadCastAllList();

	List<ArticleDto> articleAllList();

	List<VideoDto> videoAllList();

	List<ArticleDto> articleSearchList(int s_idx);

	List<VideoDto> videoSearchList(int s_idx);
}
