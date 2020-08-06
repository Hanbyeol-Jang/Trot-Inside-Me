package com.web.curation.controller;

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

import com.web.curation.dto.CoReplyDto;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.CommuReplyUser;
import com.web.curation.dto.UserDto;
import com.web.curation.service.CommuService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/community")
public class CommuController {

	@Autowired
	private UserService userService;

	@Autowired
	CommuService commuService;

	// 게시글 리스트 출력
	@ApiOperation("게시글 리스트 출력")
	@GetMapping("/list")
	public ResponseEntity<List<CommuDto>> getCommuList() {
		List<CommuDto> list = commuService.getCommuList();
		if (list != null) {
			return new ResponseEntity<List<CommuDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 게시글 추가
	@ApiOperation("게시글 추가")
	@PostMapping("/add")
	public ResponseEntity<String> commuAdd(@RequestBody CommuDto dto, HttpServletRequest request) {
		String u_email = userService.getTokenInfo(request);
		if (u_email.equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			dto.setU_email(u_email);
			if (commuService.addCommu(dto)) {
				return new ResponseEntity<String>("커뮤니티 게시물 추가 완료", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("커뮤니티 게시물 추가 에러 ", HttpStatus.NOT_FOUND);
			}
		}
	}

	// 게시글 삭제
	@ApiOperation("게시글 삭제")
	@DeleteMapping("/delete/{co_idx}")
	public ResponseEntity<String> deleteCommu(@PathVariable int co_idx) {

		if (commuService.deleteCommu(co_idx)) {
			return new ResponseEntity<String>("커뮤니티 게시물 삭제 완료", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("커뮤니티 게시물 삭제 에러 ", HttpStatus.NOT_FOUND);
		}
	}

	// 게시글 디테일 + 댓글 리스트 출력
	@ApiOperation("게시글 디테일 + 댓글 리스트 출력")
	@GetMapping("/detail/{co_idx}")
	public ResponseEntity<List<CommuReplyUser>> getDetail(@PathVariable int co_idx) {
		List<CommuReplyUser> list = commuService.getCommuDetail(co_idx);
		if (list != null) {
			return new ResponseEntity<List<CommuReplyUser>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 댓글 추가
	@ApiOperation("댓글 추가")
	@PostMapping("/reply/add/{co_idx}")
	public ResponseEntity<String> addCommuReply(@RequestBody CoReplyDto dto, @PathVariable int co_idx,
			HttpServletRequest request) {
		String u_email = userService.getTokenInfo(request);
		if (u_email.equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			dto.setU_email(u_email);
			dto.setCo_idx(co_idx);
			if (commuService.addCommuReply(dto)) {
				return new ResponseEntity<String>("댓글 추가 완료", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("댓글 추가 에러 ", HttpStatus.NOT_FOUND);
			}
		}
	}

	// 댓글 삭제
	@ApiOperation("댓글 삭제")
	@DeleteMapping("/reply/delete/{co_idx}/{cr_idx}")
	public ResponseEntity<String> deleteCommuReply(@PathVariable int co_idx, @PathVariable int cr_idx,
			HttpServletRequest request) {
		String u_email = userService.getTokenInfo(request);
		if (u_email.equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			CoReplyDto dto = new CoReplyDto();
			dto.setCo_idx(co_idx);
			dto.setCr_idx(cr_idx);
			if (commuService.deleteCommuReply(dto)) {
				return new ResponseEntity<String>("댓글 삭제 완료", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("댓글 삭제 에러 ", HttpStatus.NOT_FOUND);
			}
		}
	}

}
