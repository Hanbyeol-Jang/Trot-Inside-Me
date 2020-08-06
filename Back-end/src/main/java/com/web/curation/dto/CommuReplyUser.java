package com.web.curation.dto;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuReplyUser {
	private int co_idx;
	private String co_content;
	private String co_img;
	private String co_name;
	private Timestamp co_date;
	private int cr_idx;
	private String cr_content;
	private String cr_name;
	private int cr_cnt;
	
}
