package com.web.curation.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


public class CommuPost {
	private MultipartFile co_img;
	private String co_content;
	private String co_email;
	public MultipartFile getCo_img() {
		return co_img;
	}
	public void setCo_img(MultipartFile co_img) {
		this.co_img = co_img;
	}
	public String getCo_content() {
		return co_content;
	}
	public void setCo_content(String co_content) {
		this.co_content = co_content;
	}
	public String getCo_email() {
		return co_email;
	}
	public void setCo_email(String co_email) {
		this.co_email = co_email;
	}
	@Override
	public String toString() {
		return "CommuPost [co_img=" + co_img + ", co_content=" + co_content + ", co_email=" + co_email + "]";
	}
	
	
	
}
