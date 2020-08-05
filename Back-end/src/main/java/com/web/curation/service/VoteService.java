package com.web.curation.service;

import com.web.curation.dto.VoteDto;

public interface VoteService {

	/* 투표하기 */
	boolean voteClick(VoteDto voteDto);
}
