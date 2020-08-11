package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BoardPK;

import com.web.curation.dto.ReplyDto;

public interface BoardDao {

	/* 좋아요 클릭 */
	int goodClick(BoardPK boardPK);

	/* 좋아요 취소 */
	int goodClickCancel(BoardPK boardPK);

	/* 좋아요 수 표시 */
	int goodCount(BoardPK boardPK);

	/* 댓글 작성 */
	int addComment(ReplyDto replyDto);

	/* 댓글 삭제 */
	int deleteComment(ReplyDto replyDto);

	/* 댓글 수 표시 */
	int commentCount(BoardPK boardPK);

	//전체 리스트 출력 (1-영상 2-기사)
	List<BoardDto> getList(int b_type);

	//가수 검색에서 리스트 출력 (1-영상 2-기사)
	List<BoardDto> getSingerlist(BoardDto bdto);
}
