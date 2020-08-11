package com.web.curation.service;

import com.web.curation.dto.UserDto;

public interface KakaoService {
	public Boolean isEmail(String email); // id가 디비에 있는지 확인
	public void insertKakao(UserDto userDto); // 카카오 회원가입
	public boolean isAccessToken(UserDto userDto); // 토큰 값이 일치하는지 확인
	public void updateAccessToken(UserDto userDto); // 토큰 변경
}
