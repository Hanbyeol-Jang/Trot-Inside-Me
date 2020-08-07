
package com.web.curation.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuDto {
	private int co_idx;
	private String co_content;
	private String co_img;
	private String u_email;
	private Timestamp co_date;
}
