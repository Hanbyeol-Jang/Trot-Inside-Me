package com.web.curation.dao;

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
}
