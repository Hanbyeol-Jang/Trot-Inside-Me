
package com.web.curation.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommuDto {
	private int co_idx;
	private String co_content;
	private String co_img;
	private Timestamp co_date;
	private String co_email;
	private String co_name;
	private int co_cnt; // 게시글 총 갯수 
	private int cr_cnt; // 댓글 수
	
	private int good_cnt; // 좋아요 수 
	private int good_flag ; //좋아요 클릭 유무 
}
