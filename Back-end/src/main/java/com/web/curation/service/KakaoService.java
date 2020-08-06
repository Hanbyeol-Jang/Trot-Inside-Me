package com.web.curation.service;

import com.web.curation.dto.UserDto;

public interface KakaoService {
	public Boolean isEmail(String email); // id가 디비에 있는지 확인
	public void insertKakao(UserDto userDto); // 카카오 회원가입
}
