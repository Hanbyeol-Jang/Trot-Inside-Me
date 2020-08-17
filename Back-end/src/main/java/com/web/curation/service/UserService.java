package com.web.curation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.UserDto;

public interface UserService {
	/* 회원가입 */
	// public boolean join(UserDto user);

	/* 로그인 */
	public UserDto getUserInfo(String u_email);

	public String createToken(UserDto dto);

	// /* 회원정보 변경 */
//	public UserDto edit(UserDto userDto) throws SQLException;
//
//	/* 회원정보 삭제 */
//	public void delete(UserDto userDto) throws SQLException;
//	
//	/* 비밀번호 찾기 */
//	public String findPassword(String email) throws SQLException;
//
//	//비밀번호 변경
//	public void changePassword(UserDto userDto);

	// 접속
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
