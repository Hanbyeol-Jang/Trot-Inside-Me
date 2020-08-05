package com.web.curation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.FollowDto;
import com.web.curation.dto.UserDto;
import com.web.curation.service.UserService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto user, HttpServletRequest request) {
		
		String token = service.createToken(user.getU_email(), user.getU_pw());
        return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@GetMapping("/test")
	public ResponseEntity<UserDto> test(HttpServletRequest request){
		String tokenInfo = service.getTokenInfo(request);
		if(tokenInfo.equals("F")) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}else {
			UserDto user = service.getUserInfoToken(tokenInfo);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody UserDto user) {
		if(service.join(user)) {
			 return new ResponseEntity<>("가입 성공", HttpStatus.OK);
		}else {
			 return new ResponseEntity<>("가입 실패", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/followlist")
	public ResponseEntity<List<FollowDto>> followlist(HttpServletRequest request){
		
		String tokenInfo_userEmail = service.getTokenInfo(request);
		if(tokenInfo_userEmail.equals("F")) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}else {
			List<FollowDto> list = service.getFollowList(tokenInfo_userEmail);
			return new ResponseEntity<List<FollowDto>>(list, HttpStatus.OK );
		}
	}
	
	@GetMapping("/follow/{s_idx}")
	public ResponseEntity<FollowDto> singerDetail(@RequestParam int s_idx) {
		
		
		return null;
	}

//	임시로 팔로우 넣어줌 
//	@GetMapping("/follow/apply")
//	public ResponseEntity<String> Follow() {
//		// 임영웅 꺼를 눌렀다고 가정
//		// 유저 아이디가 a1234 라고 가정
//		// favorite 테이블에 입력
//		FollowDto dto = new FollowDto();
//		dto.setU_email("a1234");
//		dto.setS_idx(1);
//
//		if (service.followApply(dto)) {
//			return new ResponseEntity<>("팔로우 추가 성공", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("팔로우 추가 실패", HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@GetMapping("/follow/delete")
//	public Object FollowDelete() {
//		// 유저 아이디가 a1234 라고 가정
//		// favorite 테이블에서 삭제
//		if (service.followDelete("a1234")) {
//			return new ResponseEntity<>("팔로우 삭제 성공", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("팔로우 삭제 실패", HttpStatus.NOT_FOUND);
//		}
//	}
	
	
	
//수정
//	@PostMapping("/accounts/logout")
//	@ApiOperation(value = "로그아웃")
//	public String logout(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.invalidate();
//		return "index";
//	}
//
//	@PostMapping("accounts/password/change")
//	@ApiOperation(value = "비밀번호 변경")
//	public ResponseEntity<String> changepassword(@RequestBody String email, String new_password1, String new_password2,
//			String old_password) {
//		System.out.println("logger - accounts/password/change");
//		UserDto userDto = new UserDto();
//		if (new_password1.equals(new_password2)) {
//			System.out.println("패스워드 일치");
//			try {
//
//				if ((userService.findPassword(email)).equals(old_password)) {
//					// 변경
//					userDto.setU_email(email);
//					userDto.setU_pw(new_password1);
//					System.out.println(userDto.toString());
//					userService.changePassword(userDto);
//				} else {
//					System.out.println("불일치");
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} else {
//			System.out.println("패스워드 불일치");
//		}
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
}
