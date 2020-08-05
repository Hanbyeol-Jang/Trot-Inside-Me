package com.web.curation.service;

import com.web.curation.dto.KakaoDto;

public interface KakaoService {
	public Boolean isId(String id); // id가 디비에 있는지 확인
	public void insertKakao(KakaoDto kakaoDto); // 카카오 회원가입
}
