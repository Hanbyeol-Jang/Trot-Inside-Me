package com.web.curation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.KakaoDao;
import com.web.curation.dto.KakaoDto;

@Service
public class KakaoServiceImpl implements KakaoService {
	
	@Autowired
	private KakaoDao kakaoDao;
	
	@Override
	public Boolean isId(String id) {
		return kakaoDao.isId(id);
	}

	@Override
	public void insertKakao(KakaoDto kakaoDto) {
		kakaoDao.insertKakao(kakaoDto);
	}

}
