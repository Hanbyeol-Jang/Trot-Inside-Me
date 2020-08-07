package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReplyUser;

public interface CommuService {

	//게시글 리스트 출력
	List<CommuDto> getCommuList();
	//게시글 추가
	boolean addCommu(CommuDto dto);
	//게시글 삭제
	boolean deleteCommu(int co_idx);
	//게시글 디테일 + 댓글 리스트 
	List<CommuReplyUser> getCommuDetail(int co_idx);
	//게시글 댓글 추가
	boolean addCommuReply(CoReplyDto dto);
	//게시글 댓글 삭제 
	boolean deleteCommuReply(CoReplyDto dto);

}
