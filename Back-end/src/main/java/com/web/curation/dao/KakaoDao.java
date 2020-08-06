package com.web.curation.dao;

import com.web.curation.dto.UserDto;

public interface KakaoDao {
	public Boolean isEmail(String Email); //email db에 있는지 확인

	public void insertKakao(UserDto userDto); // 카카오 회원가입
	
}
