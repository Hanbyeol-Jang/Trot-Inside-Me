package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.UserDao;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.UserDto;
import com.web.curation.util.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public UserDto edit(UserDto userDto) throws SQLException {
		System.out.println("=== edit ===");
		return userDao.edit(userDto);
	}

	@Override
	public void delete(UserDto userDto) throws SQLException {
		System.out.println("=== delete ===");
		userDao.delete(userDto);
	}

	@Override
	public String findPassword(String email) throws SQLException {
		System.out.println("=== findPassword ===");
		return userDao.findPassword(email);
	}

	@Override
	public void changePassword(UserDto userDto) {
		System.out.println("=== changePassword ===");
		userDao.changePassword(userDto);
	}

	@Override
	public boolean join(UserDto user) {
		return userDao.join(user)==1;
	}


	@Override
	public String createToken(String email, String password) {
		UserDto member = userDao.getUserInfoSuc(email); 

		String token = "";
		if (!password.equals(member.getU_pw())) { 
			System.out.println("비밀번호 다름");
		} else {
			token = jwtTokenProvider.createToken(email);
			System.out.println("비밀번호 같음 token : " + token);
		}
		return token; 
	}



	@Override
	public String getTokenInfo(HttpServletRequest request) {
		return jwtTokenProvider.getInfo(request);
	}

	@Override
	public UserDto getUserInfoToken(String email) {
		return userDao.getUserInfoSuc(email);
	}

	@Override
	public List<FollowDto> getFollowList(String userEmail) {
		return userDao.getFollowList(userEmail);
	}

}
