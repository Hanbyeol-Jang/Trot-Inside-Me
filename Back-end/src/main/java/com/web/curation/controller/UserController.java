package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

	//.마이페이지 팔로우 리스트
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

	//마이페이지 팔로우 리스트(이메일)
	@ApiOperation("마이페이지 팔로우 리스트(이메일)")
	@GetMapping("/followlist/{u_email}")
	public ResponseEntity<List<FollowDto>> followlistEmail(@PathVariable String u_email) {
		try {
			List<FollowDto> list = userService.getFollowList(u_email);
			return new ResponseEntity<List<FollowDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	//마이페이지 영상 리스트"
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

	//마이페이지 기사  리스트
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

	//이메일 영상 리스트
	@ApiOperation("이메일 영상 리스트")
	@GetMapping("/videolist/{u_email}")
	public ResponseEntity<List<BoardDto>> videolistEmail(@PathVariable String u_email, @RequestParam("page") int page) {
		try {
			GoodDto gdto = new GoodDto();
			gdto.setB_type(1);
			gdto.setU_email(u_email);
			List<BoardDto> list = userService.myBoardList(gdto);

			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			
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

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	//이메일 기사  리스트
	@ApiOperation("이메일 기사  리스트")
	@GetMapping("/articlelist/{u_email}")
	public ResponseEntity<List<BoardDto>> articlelistEmail(@PathVariable String u_email,
			@RequestParam("page") int page) {
		try {
			GoodDto gdto = new GoodDto();
			gdto.setB_type(2);
			gdto.setU_email(u_email);
			List<BoardDto> list = userService.myBoardList(gdto);

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

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	//팔로우 등록
	@ApiOperation("팔로우 등록 ")
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
			if (isfollow == 1) {
				if (userService.followDelete(dto)) {
					isfollow = 0;
				}
			} else { 
				if (userService.followApply(dto)) {
					isfollow = 1;
				}
			}
			return new ResponseEntity<Integer>(isfollow, HttpStatus.OK);
		}
	}

	// 토큰 유저 정보 반환
	@ApiOperation("토큰  유저 정보 반환")
	@GetMapping("/getUserInfo")
	public ResponseEntity<UserDto> getUserInfo(HttpServletRequest request) {
		String token = request.getHeader("token");
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

	/* 커뮤니티에서 다른 사람 프로필 클릭 했을때 정보 가져오기 */
	@ApiOperation("이메일로 다른 사람 정보 가져오기")
	@GetMapping("/getUserInfo/{u_email}")
	public ResponseEntity<HashMap<String, Object>> getUserInfoEmail(@PathVariable String u_email) {
		try {
			UserDto userDto = userService.getUserInfo(u_email);
			HashMap<String, Object> map = new HashMap<>();
			map.put("userInfo", userDto);
			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
