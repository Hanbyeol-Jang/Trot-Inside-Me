package com.web.curation.dao;

import com.web.curation.dto.KakaoDto;

public interface KakaoDao {
	public Boolean isId(String id); //id db에 있는지 확인

	public void insertKakao(KakaoDto kakaoDto); // 카카오 회원가입
	
}
