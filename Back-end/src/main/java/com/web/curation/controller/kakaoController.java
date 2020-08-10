package com.web.curation.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.curation.dto.UserDto;
import com.web.curation.service.KakaoService;
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
	private HttpSession session;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// #삭제 예정
	@GetMapping("/index")
	public String index() throws SQLException {
		return "index";
	}

	// #삭제 예정 - 프론트에서 구현하면 필요 없음
	@GetMapping("/social/login")
	public String login() throws SQLException {
		String URL = "https://kauth.kakao.com/oauth/authorize?client_id=78183e66919b34b25f731ea9f2d99f0e"
				+ "&redirect_uri=http://localhost:8080/social/login/kakao" + "&response_type=code";
//		System.out.println(URL);
		return "redirect:" + URL;
	}

	//# 삭제 예정 - 프론트에서 access값을 넘겨주면 필요 없음
	@GetMapping("/social/login/kakao")
	public ResponseEntity<String> login(@RequestParam("code") String code) throws SQLException {
		System.out.println("logger - kakao login 후에 getCode");
		System.out.println("code : " + code);
		System.out.println("logger - code를 기반으로 getAccessToken");
		String access_Token = "";
		try {
			access_Token = kakaoAPI.getAccessToken(code);
			System.out.println("controller access_token : " + access_Token);
			return new ResponseEntity<String>(access_Token, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}
	}
	// # 삭제 예정
	@PostMapping("/kakao/login/getInfo")
	public ResponseEntity<HashMap<String, Object>> getInfo(@RequestParam String access_Token) throws SQLException {
		HashMap<String, Object> userInfo = null;
		UserDto userDto = new UserDto();
		session = null;
		try {
			userDto = kakaoAPI.getUserInfo(access_Token);

			System.out.println("login Controller : " + userInfo);
//      클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
//  if (userInfo.get("email") != null) {
//      session.setAttribute("userId", userInfo.get("email"));
//      session.setAttribute("access_Token", access_Token);
//  }
			return new ResponseEntity<HashMap<String, Object>>(userInfo, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HashMap<String, Object>>(userInfo, HttpStatus.NOT_FOUND);
		}

	}

	// access_Token을 받아 얻어온 user의 정보가 db에 있는지 확인
	@PostMapping(value = "/signin/kakao")
	public ResponseEntity<String> login(HttpServletRequest request) {
		// TODO 배포시 Syso 삭제
		System.out.println("logger - login 후 헤더에 access_token 담아 전송 ");
		String access_token = jwtTokenProvider.getAccessToken(request);
		System.out.println("logger - access_token: ");
		UserDto userDto = kakaoAPI.getUserInfo(access_token);
		System.out.println(userDto);
		
		if (kakaoService.isEmail(userDto.getU_email())) {
			System.out.println("logger - 회원 정보가 db에 있습니다");
		} else {
			System.out.println("logger - 회원 정보가  db에 없습니다.");
			// 디비 삽입
			kakaoService.insertKakao(userDto);
		}
		// TODO jinyong 수정해야됨
		String Token = jwtTokenProvider.createToken(userDto.getU_email());
		System.out.println(Token);
		return new ResponseEntity<String>(Token, HttpStatus.OK);

	}
	// 로그아웃  access token과 refresh token을 만료시킴
	@PostMapping(value = "/kakao/token/logout")
	@ApiOperation("로그아웃")
	public void kakaoLogout(@RequestParam String access_Token) {
		kakaoAPI.kakaoLogout(access_Token);
	}

	// 로그아웃 앱서비스와의 연동을 끊음 => 카카오 로그인 탈퇴시 사용
	@PostMapping(value = "/kakao/unlink")
	@ApiOperation("로그아웃")
	public void kakaoUnlink(@RequestParam String access_Token) {
		kakaoAPI.kakaoUnlink(access_Token);
	}
	// 카카오 계정과 함께 로그아웃
	@GetMapping(value = "/kakao/logout")
	@ApiOperation("카카오계정과함께 로그아웃")
	public String logout() {
		String url = "https://kauth.kakao.com/oauth/logout?client_id=78183e66919b34b25f731ea9f2d99f0e&logout_redirect_uri="
				+ "http://localhost:8080/index";
		;
		return "redirect:" + url;
	}
}
