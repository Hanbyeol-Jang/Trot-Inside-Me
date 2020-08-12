package com.web.curation.dto;

import lombok.Data;

@Data
public class BoardDto {
	private int b_idx;
	private int b_type;
//	private int b_good;
//	private int b_bookmard;
//	private int b_reply;
	private String b_title;
	private String b_thumbnail;
	private String b_url;
	private String b_date;
	private String b_company;
	private String b_member;
	private String b_content;
}
