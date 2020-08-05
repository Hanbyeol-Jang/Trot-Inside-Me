package com.web.curation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.AdminDto;
import com.web.curation.dto.UserDto;
import com.web.curation.service.AdminServcie;
import com.web.curation.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminServcie adminService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto user, HttpServletRequest request, HttpSession session) {
		
		//관리자 로그인 할때 다음 페이지가 관리자가 아니면 안넘어갈 수 있도록 하는 방법이 있는 지 찾아보기. -> 인터 셉터?
		String token = userService.createToken(user.getU_email(), user.getU_pw());
		if(token != null) {
			session.setAttribute("u_email", user.getU_email());
			session.setAttribute("u_name", user.getU_name());
		}
        return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@GetMapping("/test")
	public ResponseEntity<UserDto> test(HttpServletRequest request){
		String tokenInfo = userService.getTokenInfo(request);
		if(tokenInfo.equals("F")) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}else {
			UserDto user = userService.getUserInfoToken(tokenInfo);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
	

	//관리자 - 편성표 주소 추가
	@PostMapping("/broadSchedule")
	public ResponseEntity<String> broadSchedule(@RequestBody AdminDto dto) {
		if(adminService.addBroadSchedule(dto)) {
			return new ResponseEntity<String>("편성표 추가 완료",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("편성표 추가 에러 ",HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/broadSchedule/{a_idx}")
	public ResponseEntity<String> DeleteBroadSchedule(@PathVariable int a_idx) {
		if(adminService.deleteBroadSchedule(a_idx)) {
			return new ResponseEntity<String>("편성표 삭제 완료",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("편성표 삭제 에러 ",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/broadSchedule/list")
	public ResponseEntity<List<AdminDto>> broadSchedulelist(){
		List<AdminDto> list = adminService.getBroadScheduleList();
		if(list!=null) {
			return new ResponseEntity<List<AdminDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
}
