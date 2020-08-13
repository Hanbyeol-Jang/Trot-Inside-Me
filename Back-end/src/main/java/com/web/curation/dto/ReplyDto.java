package com.web.curation.dto;

import lombok.Data;

@Data
public class ReplyDto {
	private int r_idx;
	private String r_content;
	private String u_email;
	private int b_type;
	private int b_idx;

}
