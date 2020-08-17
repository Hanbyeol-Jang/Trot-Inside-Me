package com.web.curation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.UserDto;

public interface UserService {

	/* 로그인 */
	public UserDto getUserInfo(String u_email);

	// 토큰 생성
	public String createToken(UserDto dto);

	// 토큰 - 회원정보 조회

	public UserDto getTokenInfo(HttpServletRequest request);

	// 팔로우 리스트
	public List<FollowDto> getFollowList(String tokenInfo_userEmail);

	// 팔로우 추가
	public boolean followApply(FollowDto dto);

	// 팔로우 삭제
	public boolean followDelete(FollowDto dto);

	// 마이페이지 영상, 기사 리스트
	public List<BoardDto> myBoardList(GoodDto gdto);

}
