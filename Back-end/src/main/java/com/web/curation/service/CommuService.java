package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.CoGoodDto;
import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReply;

public interface CommuService {

	// 게시글 리스트 출력
	List<CommuDto> getCommuList(String u_email, String string);

	// 게시글 추가
	boolean addCommu(CommuDto dto);

	// 게시글 수정
	boolean updateDetail(CommuDto dto);

	// 게시글 삭제
	boolean deleteCommu(int co_idx);

	// 게시글 디테일
	CommuDto getCommuDetail(CoGoodDto gdto);

	// 게시글 디테일 댓글 리스트
	List<CommuReply> getDetailReplyList(int co_idx);

	// 게시글 디테일에서 삭제
	boolean deleteDetail(int co_idx);

	// 게시글 댓글 추가
	boolean addCommuReply(CoReplyDto dto);

	// 게시글 댓글 삭제
	boolean deleteCommuReply(CoReplyDto dto);

	// 좋아요 선택
	boolean clickGood(CoGoodDto dto);

	// 좋아요 취소
	boolean clickGoodCancel(CoGoodDto dto);

	// 이미지 주소
	String getImgUrl(int co_idx);

}
