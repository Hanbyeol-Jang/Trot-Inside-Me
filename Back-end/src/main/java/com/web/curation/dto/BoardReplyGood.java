package com.web.curation.dto;

import lombok.Data;

@Data
public class BoardReplyGood {
	private int b_idx;
	private int b_type;
	private String b_title;
	private String b_content;
	private String b_thumbnail;
	private String b_url;
	private String b_date;
	private String b_member;

	private int reply_cnt; // 댓글 수
	private int b_cnt;// 전체 게시글 수

	private int good_cnt; // 좋아요 수
	private int isgood; // 좋아요 유무 (1=선택 0=미선택)
}
