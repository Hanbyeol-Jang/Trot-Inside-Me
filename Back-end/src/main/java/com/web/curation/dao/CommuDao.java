package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReplyUser;

public interface CommuDao {

	//게시글 리스트 
	List<CommuDto> getCommuList();
	//게시글 추가
	int addCommu(CommuDto dto);
	//게시글 삭제
	int deleteCommu(int co_idx);
	//게시글 디테일 + 댓글 리스트
	List<CommuReplyUser> getCommuDetail(int co_idx);
	// 게시글 댓글 추가
	int addCommuReply(CoReplyDto dto);
	//게시글 댓글 삭제 
	int deleteCommuReply(CoReplyDto dto);
	//좋아요 선택 
	int clickGood(CoGoodDto dto);

}
