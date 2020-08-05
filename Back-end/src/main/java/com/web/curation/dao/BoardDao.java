package com.web.curation.dao;

import com.web.curation.dto.BoardPK;

public interface BoardDao {

	/* 좋아요 클릭 */
	int goodClick(BoardPK boardPK);

	/* 좋아요 취소 */
	int goodClickCancel(BoardPK boardPK);

	/* 좋아요 수 표시 */
	int goodCount(BoardPK boardPK);

}
