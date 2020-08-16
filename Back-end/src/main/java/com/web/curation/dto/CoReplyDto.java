package com.web.curation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoReplyDto {
	private int cr_idx;
	private String cr_content;
	private String u_email;
	private int co_idx;
}
