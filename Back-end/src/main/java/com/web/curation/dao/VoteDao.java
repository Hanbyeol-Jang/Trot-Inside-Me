package com.web.curation.dao;

import com.web.curation.dto.VoteDto;

public interface VoteDao {
	/* 투표하기 */
	int voteClick(VoteDto voteDto);

}
