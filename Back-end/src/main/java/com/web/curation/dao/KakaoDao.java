package com.web.curation.dao;

import com.web.curation.dto.UserDto;

public interface KakaoDao {
	public Boolean isEmail(String Email); //email db에 있는지 확인

	public void insertKakao(UserDto userDto); // 카카오 회원가입

	public boolean isAccesstoken(UserDto userDto); // access_token이 db에 있는 값과 일치하는지 확인

	public void updateAccessToken(UserDto userDto); //access_token 변경
	
}
