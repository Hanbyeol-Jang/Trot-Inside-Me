package com.web.curation.service;

import com.web.curation.dto.BoardPK;
import com.web.curation.dto.ReplyDto;

public interface BoardService {

	/* 좋아요 클릭 */
	boolean goodClick(BoardPK boardPK);

	/* 좋아요 취소 */
	boolean goodClickCancel(BoardPK boardPK);

	/* 좋아요 수 표시 */
	int goodCount(BoardPK boardPK);

	/* 댓글 작성 */
	boolean addComment(ReplyDto replyDto);

	/* 댓글 삭제 */
	boolean deleteComment(ReplyDto replyDto);

	/* 댓글 수 표시 */
	int commentCount(BoardPK boardPK);

}
