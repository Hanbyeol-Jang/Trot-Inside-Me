package com.web.curation.dto;

import lombok.Setter;

public class CoGoodDto {
	private int co_idx;
	private String u_email;
	public CoGoodDto(int co_idx, String u_email) {
		super();
		this.co_idx = co_idx;
		this.u_email = u_email;
	}
	
}
