package com.web.curation.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private HttpSession session;
	@Autowired
	private UserService userService;
	@Value("${KAKAO_API_KEY}")
	private String API_KEY;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// #삭제 예정 - 프론트에서 구현하면 필요 없음
	@GetMapping("/social/login")
	public String login() throws SQLException {
		String URL = "https://kauth.kakao.com/oauth/authorize?client_id=78183e66919b34b25f731ea9f2d99f0e"
				+ "&redirect_uri=http://localhost:8080/social/login/kakao" + "&response_type=code";

		return "redirect:" + URL;
	}

	// # 삭제 예정 - 프론트에서 access값을 넘겨주면 필요 없음
	@GetMapping("/social/login/kakao")
	public ResponseEntity<String> login(@RequestParam("code") String code) throws SQLException {

		String access_Token = "";
		try {
			access_Token = kakaoAPI.getAccessToken(code);

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
			return new ResponseEntity<HashMap<String, Object>>(userInfo, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HashMap<String, Object>>(userInfo, HttpStatus.NOT_FOUND);
		}

	}

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



	// #삭제예정 
	// 로그아웃 access token과 refresh token을 만료시킴
	@PostMapping(value = "/kakao/token/logout")
	@ApiOperation("로그아웃")
	public void kakaoLogout(@RequestParam String access_Token) {
		kakaoAPI.kakaoLogout(access_Token);
	}

	// 로그아웃 앱서비스와의 연동을 끊음 => 카카오 로그인 탈퇴시 사용
	@PostMapping(value = "/user/kakao/unlink")
	@ApiOperation("로그아웃")
	public void kakaoUnlink(HttpServletRequest request) {
		String useremail = jwtTokenProvider.getInfo(request).getU_email();
		String accessToken = userService.getUserInfo(useremail).getU_accessToken();

		// TODO 우리 디비에서 해당 계정 삭제해줘야함.

		kakaoAPI.kakaoUnlink(accessToken);
		kakaoService.deleteKakao(useremail);
	}

	// 카카오 계정과 함께 로그아웃
	@GetMapping(value = "/kakao/logout")
	@ApiOperation("카카오계정과함께 로그아웃")
	public String logout() {
		String url = "https://kauth.kakao.com/oauth/logout?client_id=" + API_KEY + "&logout_redirect_uri="
				+ "http://localhost:8080/";
		return "redirect:" + url;
	}
}
