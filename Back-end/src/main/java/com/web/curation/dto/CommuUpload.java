package com.web.curation.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CommuUpload {
	private String co_content;
	private MultipartFile co_img;
}
