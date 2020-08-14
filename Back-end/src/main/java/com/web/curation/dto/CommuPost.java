package com.web.curation.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CommuPost {
	private MultipartFile co_img;
	private String co_content;
	private String co_email;
}
