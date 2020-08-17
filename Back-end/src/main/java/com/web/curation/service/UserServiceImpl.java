package com.web.curation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.UserDao;
import com.web.curation.dto.BoardDto;
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
		return userDao.followApply(dto) == 1;
	}

	@Override
	public boolean followDelete(FollowDto dto) {
		return userDao.followDelete(dto) == 1;
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
			e.printStackTrace();
		}

		String token = "";
		if (!dto.getU_pw().equals(member.getU_pw())) {
		} else {
			token = jwtTokenProvider.createToken(dto);
		}
		return token;
	}

}
