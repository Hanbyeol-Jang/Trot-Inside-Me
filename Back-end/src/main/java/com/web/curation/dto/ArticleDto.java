package com.web.curation.dto;

import lombok.Data;

@Data
public class ArticleDto {
	private int b_type;
	private int a_index;
	private String a_url;
	private String a_title;
	private String a_content;
	private String a_img;
	private String a_member;
}
