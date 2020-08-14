package com.web.curation.dto;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuReply {
	private int co_idx; //게시물 번호
	private int cr_idx; // 댓글 번호
	private String cr_content; //댓글 내
	private String cr_email; //댓글 작성자 이메일
	private String cr_name; //댓글 작성자 이름 
	private Timestamp cr_date; //댓글 시간 
}
