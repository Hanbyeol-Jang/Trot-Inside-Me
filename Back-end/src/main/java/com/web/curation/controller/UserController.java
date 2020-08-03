package com.web.curation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
