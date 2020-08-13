package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.CommuDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.UserDto;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation("마이페이지 팔로우 리스트")
	@GetMapping("/followlist")
	public ResponseEntity<List<FollowDto>> followlist(HttpServletRequest request) {

		UserDto dto = userService.getTokenInfo(request);
		if (dto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			List<FollowDto> list = userService.getFollowList(dto.getU_email());
			return new ResponseEntity<List<FollowDto>>(list, HttpStatus.OK);
		}
	}

	@ApiOperation("마이페이지 영상 리스트")
	@GetMapping("/videolist")
	public ResponseEntity<List<BoardDto>> videolist(@RequestParam("page") int page, HttpServletRequest request) {
		UserDto dto = userService.getTokenInfo(request);
		if (dto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		GoodDto gdto = new GoodDto();
		gdto.setB_type(1);
		gdto.setU_email(dto.getU_email());
		List<BoardDto> list = userService.myBoardList(gdto);
		if (list != null) {
			List<BoardDto> showList = new ArrayList<>();
			int lastPageRemain = list.size() % 5;
			int lastPage = list.size() - lastPageRemain;
			page = 5 * page - 5;
			// 5개씩 보여주기
			if (page < lastPage) {
				for (int i = page; i < page + 5; i++) {
					showList.add(list.get(i));
				}
			} else if (page == lastPage) {
				for (int i = page; i < page + lastPageRemain; i++) {
					showList.add(list.get(i));
				}
			}
			return new ResponseEntity<List<BoardDto>>(showList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@ApiOperation("마이페이지 기사  리스트")
	@GetMapping("/articlelist")
	public ResponseEntity<List<BoardDto>> articlelist(@RequestParam("page") int page, HttpServletRequest request) {
		UserDto dto = userService.getTokenInfo(request);
		if (dto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		GoodDto gdto = new GoodDto();
		gdto.setB_type(2);
		gdto.setU_email(dto.getU_email());
		List<BoardDto> list = userService.myBoardList(gdto);
		if (list != null) {
			List<BoardDto> showList = new ArrayList<>();
			int lastPageRemain = list.size() % 5;
			int lastPage = list.size() - lastPageRemain;
			page = 5 * page - 5;
			// 5개씩 보여주기
			if (page < lastPage) {
				for (int i = page; i < page + 5; i++) {
					showList.add(list.get(i));
				}
			} else if (page == lastPage) {
				for (int i = page; i < page + lastPageRemain; i++) {
					showList.add(list.get(i));
				}
			}
			return new ResponseEntity<List<BoardDto>>(showList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/follow/{s_idx}")
	public ResponseEntity<Integer> followApply(@PathVariable int s_idx, HttpServletRequest request,
			@RequestParam("isfollow") int isfollow) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			FollowDto dto = new FollowDto();
			dto.setU_email(udto.getU_email());
			dto.setS_idx(s_idx);
			
			if (isfollow == 1) { // 좋아요 취소
				if (userService.followDelete(dto)) {
					isfollow = 0;
				}
			} else { // 좋아요 선택
				if (userService.followApply(dto)) {
					isfollow = 1;
				}
			}
			return new ResponseEntity<Integer>(isfollow, HttpStatus.OK);
		}
	}
//
//	@GetMapping("/followdelete/{s_idx}")
//	public Object FollowDelete(@PathVariable int s_idx, HttpServletRequest request) {
//		FollowDto dto = new FollowDto();
//		dto.setU_email(userService.getTokenInfo(request).getU_email());
//		dto.setS_idx(s_idx);
//		if (userService.followDelete(dto)) {
//			return new ResponseEntity<>("팔로우 삭제 성공", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("팔로우 삭제 실패", HttpStatus.NOT_FOUND);
//		}
//	}

	// 토큰 디코딩.. 유저 정보 반환
	@ApiOperation("토큰 디코딩.. 유저 정보 반환")
	@GetMapping("/getUserInfo")
	public ResponseEntity<UserDto> getUserInfo(HttpServletRequest request) {
		String token = request.getHeader("token");
		System.out.println("token-getuserinfo :"+ token);
		if (token.equals("null")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		UserDto dto = userService.getTokenInfo(request); // 헤더에서 유저정보 추출
		if (dto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(dto, HttpStatus.OK);
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
