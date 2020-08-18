package com.web.curation.dao;

import java.util.HashMap;
import java.util.List;

import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReply;

public interface CommuDao {

	// 게시글 리스트
	List<CommuDto> getCommuList(HashMap<String, String> map);

	// 게시글 추가
	int addCommu(CommuDto dto);

	// 게시글 삭제
	int deleteCommu(int co_idx);

	// 게시글 수정
	int updateDetail(CommuDto dto);

	// 게시글 디테일
	CommuDto getCommuDetail(CoGoodDto gdto);

	// 게시글 디테일 댓글 리스트
	List<CommuReply> getDetailReplyList(int co_idx);

	// 게시글 댓글 추가
	int addCommuReply(CoReplyDto dto);

	// 게시글 댓글 삭제
	int deleteCommuReply(CoReplyDto dto);

	// 좋아요 선택
	int clickGood(CoGoodDto dto);

	// 좋아요 취소
	int clickGoodCancel(CoGoodDto dto);

	// 디테일에서 게시글 삭제
	int deleteDetail(int co_idx);

	// 가수, 편성표 이미지 출력
	String getImgUrl(int co_idx);

}
