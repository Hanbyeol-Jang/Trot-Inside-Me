package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.UserDao;
import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.UserDto;
import com.web.curation.util.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

//	@Override
//	public UserDto edit(UserDto userDto) throws SQLException {
//		System.out.println("=== edit ===");
//		return userDao.edit(userDto);
//	}
//
//	@Override
//	public void delete(UserDto userDto) throws SQLException {
//		System.out.println("=== delete ===");
//		userDao.delete(userDto);
//	}
//
//	@Override
//	public String findPassword(String email) throws SQLException {
//		System.out.println("=== findPassword ===");
//		return userDao.findPassword(email);
//	}
//
//	@Override
//	public void changePassword(UserDto userDto) {
//		System.out.println("=== changePassword ===");
//		userDao.changePassword(userDto);
//	}
//
//	@Override
//	public boolean join(UserDto user) {
//		return userDao.join(user)==1;
//	}
//	


	@Override
	public List<BoardDto> myBoardList(GoodDto gdto) {
		return userDao.myBoardList(gdto);
	}

	
	@Override
	public List<FollowDto> getFollowList(String userEmail) {
		return userDao.getFollowList(userEmail);
	}

	@Override
	public boolean followApply(FollowDto dto) {
		return userDao.followApply(dto)==1;
	}

	@Override
	public boolean followDelete(FollowDto dto) {
		// TODO Auto-generated method stub
		return userDao.followDelete(dto)==1;
	}

	@Override
	public UserDto getTokenInfo(HttpServletRequest request) {
		return jwtTokenProvider.getInfo(request);
	}

	@Override
	public UserDto getUserInfo(String u_email) {
		return userDao.getUserInfoSuc(u_email); 
	}

	@Override
	public String createToken(UserDto dto) {
		UserDto member = new UserDto();
		try {
			member = userDao.getUserInfoSuc(dto.getU_email()); 
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		
		System.out.println("회원정보 가져옴. "+member);
		String token = "";
		if (!dto.getU_pw().equals(member.getU_pw())) { 
			System.out.println("비밀번호 다름");
		} else {
			token = jwtTokenProvider.createToken(dto);
			System.out.println("비밀번호 같음 token : " + token);
		}
		return token; 
	}

}
