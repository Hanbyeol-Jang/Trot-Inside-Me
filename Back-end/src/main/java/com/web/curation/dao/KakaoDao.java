package com.web.curation.dao;

import com.web.curation.dto.UserDto;

public interface KakaoDao {
	public Boolean isEmail(String Email); //email db에 있는지 확인

	public void insertKakao(UserDto userDto); // 카카오 회원가입

	public void updateKakao(UserDto userDto); // 카카오 정보 업데이트

	public void deleteKakao(String email); // 카카오 서비스 탈퇴
	
}
