package com.web.curation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.curation.dto.UserDto;
import com.web.curation.service.KakaoService;
import com.web.curation.service.UserService;
import com.web.curation.util.JwtTokenProvider;
import com.web.curation.util.KakaoAPI;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
public class kakaoController {

	@Autowired
	private KakaoAPI kakaoAPI;
	@Autowired
	KakaoService kakaoService;
	@Autowired
	private UserService userService;
	@Value("${KAKAO_API_KEY}")
	private String API_KEY;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;


	// access_Token을 받아 얻어온 user의 정보가 db에 있는지 확인
	@PostMapping(value = "/signin/kakao")
	public ResponseEntity<String> login(HttpServletRequest request) {

		String access_token = jwtTokenProvider.getAccessToken(request);
		UserDto userDto = kakaoAPI.getUserInfo(access_token);
		userDto.setU_accessToken(access_token);

		// db에 회원 정보가 있는지 확인
		if (!kakaoService.isEmail(userDto.getU_email()))
			kakaoService.insertKakao(userDto);

		kakaoService.updateKakao(userDto);

		String Token = jwtTokenProvider.createToken(userDto);

		return new ResponseEntity<String>(Token, HttpStatus.OK);

	}


//	// #삭제예정 
//	// 로그아웃 access token과 refresh token을 만료시킴
//	@PostMapping(value = "/kakao/token/logout")
//	@ApiOperation("카카오 로그아웃")
//	public void kakaoLogout(@RequestParam String access_Token) {
//		kakaoAPI.kakaoLogout(access_Token);
//	}

	// 로그아웃 앱서비스와의 연동을 끊음 => 카카오 로그인 탈퇴시 사용
	@PostMapping(value = "/user/kakao/unlink")
	@ApiOperation("카카오 로그아웃 (unlink) ")
	public void kakaoUnlink(HttpServletRequest request) {
		String useremail = jwtTokenProvider.getInfo(request).getU_email();
		String accessToken = userService.getUserInfo(useremail).getU_accessToken();

		kakaoAPI.kakaoUnlink(accessToken);
		kakaoService.deleteKakao(useremail);
	}

}
