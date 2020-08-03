package com.web.curation.dto;

import lombok.Data;

@Data
public class VideoDto {
	private int b_type;
	private int b_idx;
	private String v_url;
	private String v_title;
	private String v_content;
	private String v_member;
}
