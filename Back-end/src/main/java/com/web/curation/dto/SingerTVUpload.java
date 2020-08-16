package com.web.curation.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SingerTVUpload {
	private String name;
	private MultipartFile img;
	private String url;
}
