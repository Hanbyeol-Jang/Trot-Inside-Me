package com.web.curation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private String u_email;
	private String u_name;
	private String u_profileImg;
	private String u_thumbnail;
	private String u_pw;
	private String u_accessToken;
	private int u_isAdmin; //관리자 1 ,유저 0
	private int u_hasVote ; //투표 유 1 , 무 0
}
