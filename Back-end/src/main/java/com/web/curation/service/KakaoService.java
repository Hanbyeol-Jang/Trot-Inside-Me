package com.web.curation.service;

import com.web.curation.dto.UserDto;

public interface KakaoService {

	// id가 디비에 있는지 확인
	public Boolean isEmail(String email);

	// 카카오 회원가입
	public void insertKakao(UserDto userDto);

	// 카카오 정보 업데이트
	public void updateKakao(UserDto userDto);

	// 카카오 서비스 탈퇴
	public void deleteKakao(String email);
}
