package com.web.curation.dto;

import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoDto {
	private String id;
//	private Properties properties;
//
//    @Getter
//    @Setter
//    @ToString
//    private static class Properties {
	private String nickname;
	private String thumbnail_image;
	private String profile_image;
//    }
}
