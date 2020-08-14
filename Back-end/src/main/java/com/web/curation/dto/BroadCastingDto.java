package com.web.curation.dto;

import lombok.Data;

@Data
public class BroadCastingDto {
	private int bc_idx;
	private String bc_company;
	private String bc_date;
	private String bc_time;
	private String bc_member;
	private String bc_title;
	private int a_idx;
}
