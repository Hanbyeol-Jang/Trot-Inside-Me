package com.web.curation.dto;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuReplyUser {
	private int co_idx; //게시물 번호
	private String co_content; //게시물 내용
	private String co_img; //게시물 이미지
	private Timestamp co_date; //게시물 작성일
	private String co_email; //게시물 작성자 이메일
	private String co_name; //게시물 작성자 이름
	
	private int cr_idx; // 댓글 번호
	private String cr_content; //댓글 내용
	private String cr_email; //댓글 작성자 이메일
	private String cr_name; //댓글 작성자 이름 
	
	private int cr_cnt; // 댓글 수
	
	private int good_cnt; // 좋아요 수 
	private int good_flag ; //좋아요 클릭 유무 
	
}
