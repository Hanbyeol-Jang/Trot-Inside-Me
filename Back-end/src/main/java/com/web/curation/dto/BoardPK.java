package com.web.curation.dto;

import lombok.Data;

@Data
public class BoardPK {
	private int b_idx;
	private int b_type;
	private String b_title;
	private String b_thumbnail;
	private String b_url;
	private String b_date;
	private String b_company;
	private String b_member;
	private String b_content;

	private int b_cnt; // 게시글 총 갯수
	private int br_cnt; // 댓글 수

	private int good_cnt; // 좋아요 수
	private int good_flag; // 좋아요 클릭 유무
}
