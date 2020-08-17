package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.TopDto;
import com.web.curation.dto.VoteDto;

public interface VoteDao {
	/* 투표하기 */
	int voteClick(VoteDto voteDto);

	/* 투표했는지 확인 */
	int voteCheck(String u_email);

	/* idx 이름으로 변환 */
	String idxToName(int s_idx);

	/* 가수 투표 현황 */
	int thisWeek(int s_idx);

	/* 가수 투표 현황 전체 */
	int thisWeekTotal(int s_idx);

	/* 지난주 가수 투표 */
	int lastWeek(String s_name);

	/* 지난주 가수 투표 전체 */
	int lastWeekTotal(String s_name);

	/* 이번주 순위 */
	int thisWeekRank(int s_idx);

	/* 지난주 순위 */
	int lastWeekRank(String s_name);

	/* 이번주 top 3 */
	List<TopDto> thisWeekTopThree();

	/* 지난주 top 3 */
	List<TopDto> lastWeekTopThree();

}
