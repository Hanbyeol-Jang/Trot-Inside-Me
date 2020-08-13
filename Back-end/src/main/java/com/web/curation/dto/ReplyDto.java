package com.web.curation.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyDto {
	private int r_idx;
	private String r_content;
	private String r_email;
	private String r_name;
	private String r_profileImg;
	private int b_type;
	private int b_idx;
	private Timestamp r_date;
}
