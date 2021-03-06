package com.web.curation.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.AdminDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.dto.SingerTVUpload;
import com.web.curation.dto.UserDto;
import com.web.curation.service.AdminServcie;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminServcie adminService;
	static String serverUrl = "/home/ubuntu/img/";

	// 관리자 로그인
	@ApiOperation("관리자 로그인")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto user, HttpServletRequest request) {
		UserDto dto = adminService.adminLogin(user);
		if (dto == null)
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		String token = userService.createToken(dto);

		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	// 관리자 로그아웃..
	@ApiOperation("관리자 로그아웃")
	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		return new ResponseEntity<>("로그아웃", HttpStatus.OK);
	}

	// 관리자 - 편성표 리스트 출력
	@ApiOperation("편성표 리스트 출력")
	@GetMapping("/tvlist")
	public ResponseEntity<List<AdminDto>> broadSchedulelist() {
		List<AdminDto> list = adminService.getBroadScheduleList();
		if (list != null) {
			list.remove(0);
			return new ResponseEntity<List<AdminDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 관리자 - 편성표 주소 추가
	@ApiOperation("편성표 주소 추가")
	@PostMapping("/tvadd")
	public ResponseEntity<String> broadSchedule(SingerTVUpload up) throws IllegalStateException, IOException {
		AdminDto dto = new AdminDto();

		if (up.getImg() != null) {
			String saveUrl = serverUrl + up.getImg().getOriginalFilename();
			File file = new File(saveUrl);
			up.getImg().transferTo(file);
			dto.setA_img(up.getImg().getOriginalFilename());
		}
		dto.setA_broadName(up.getName());
		if (up.getUrl() != null) {
			dto.setA_broadUrl(up.getUrl());
		}
		if (adminService.addBroadSchedule(dto)) {
			return new ResponseEntity<String>("편성표 추가 완료", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("편성표 추가 에러 ", HttpStatus.NOT_FOUND);
		}
	}

	// 관리자 - 편성표 삭제
	@ApiOperation("편성표 삭제 ")
	@DeleteMapping("/tvdelete/{a_idx}")
	public ResponseEntity<String> DeleteBroadSchedule(@PathVariable int a_idx) {
		// 이미지 처리
		String imgurl = serverUrl + adminService.getTVImgUrl(a_idx);
		if (imgurl != null) {
			File file = new File(imgurl);
			file.delete();
		}
		if (adminService.deleteBroadSchedule(a_idx)) {
			return new ResponseEntity<String>("편성표 삭제 완료", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("편성표 삭제 에러 ", HttpStatus.NOT_FOUND);
		}
	}

	// 관리자 - 가수 리스트 출력
	@ApiOperation(" 가수 리스트 출력")
	@GetMapping("/singerlist")
	public ResponseEntity<List<SingerDto>> getSingerList() {
		List<SingerDto> list = adminService.getSingerList();
		if (list != null) {
			list.remove(0);
			return new ResponseEntity<List<SingerDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 관리자 - 가수 추가
	@ApiOperation("가수 추가 ")
	@PostMapping("/singeradd")
	public ResponseEntity<String> singerAdd(SingerTVUpload up) throws IllegalStateException, IOException {
		SingerDto dto = new SingerDto();
		dto.setS_name(up.getName());
		if (up.getImg() != null) {
			String saveUrl = serverUrl + up.getImg().getOriginalFilename();
			File file = new File(saveUrl);
			up.getImg().transferTo(file);
			dto.setS_img(up.getImg().getOriginalFilename());
		}
		if (up.getUrl() != null) {
			dto.setS_cafeUrl(up.getUrl());
		}
		if (adminService.addSinger(dto)) {
			return new ResponseEntity<String>("가수 추가 완료", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("가수 추가 에러 ", HttpStatus.NOT_FOUND);
		}
	}

	// 관리자 - 가수 삭제
	@ApiOperation("가수 삭제 ")
	@DeleteMapping("/singerdelete/{s_idx}")
	public ResponseEntity<String> deleteSinger(@PathVariable int s_idx) {
		// 이미지 처리
		String imgurl = serverUrl + adminService.getSingerImgUrl(s_idx);
		if (imgurl != null) {
			File file = new File(imgurl);
			file.delete();
		}
		if (adminService.deleteSinger(s_idx)) {
			return new ResponseEntity<String>("가수 삭제 완료", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("가수 삭제 에러 ", HttpStatus.NOT_FOUND);
		}
	}

}
