package com.web.curation.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.web.curation.dto.UserDto;

public interface UserService {
	/* 회원가입 */
	public boolean join(UserDto user);

	/* 로그인 */
	public String createToken(String email, String password);

	/* 회원정보 변경 */
	public UserDto edit(UserDto userDto) throws SQLException;

	/* 회원정보 삭제 */
	public void delete(UserDto userDto) throws SQLException;
	
	/* 비밀번호 찾기 */
	public String findPassword(String email) throws SQLException;

	//비밀번호 변경
	public void changePassword(UserDto userDto);

	//접속
	public String getTokenInfo(HttpServletRequest request);
	public UserDto getUserInfoToken(String tokenInfo);


}
