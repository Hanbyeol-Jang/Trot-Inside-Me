package com.web.curation.dto;

import lombok.Data;

@Data
public class UserDto {

	private String u_email;
	private String u_name;
	private String u_profileImg;
	private String u_thumbnail;
	private String u_pw;
	private int u_isAdmin;

}
