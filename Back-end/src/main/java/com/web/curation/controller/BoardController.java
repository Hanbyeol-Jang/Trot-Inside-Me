package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.BoardPK;
import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.FollowDto;
import com.web.curation.dto.VideoDto;
import com.web.curation.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class BoardController {

	@Autowired
	private BoardService service;

	@GetMapping("/schedule/todayList")
	public ResponseEntity<List<BroadCastingDto>> todaylist() {
		List<BroadCastingDto> list = service.broadCastAllList();
		if (list != null) {
			return new ResponseEntity<List<BroadCastingDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/* 좋아요 수 표시 */
	@ApiOperation("좋아요 수 표시")
	@GetMapping("/board/good/{b_type}/{b_idx}")
	public ResponseEntity<HashMap<String, Object>> goodCount(@PathVariable("b_type") int b_type,
			@PathVariable("b_idx") int b_idx) {
		try {
			BoardPK boardPK = new BoardPK();
			boardPK.setB_type(b_type);
			boardPK.setB_idx(b_idx);

			int like_count = service.goodCount(boardPK);
			HashMap<String, Object> map = new HashMap<>();
			map.put("like_count", like_count);
			map.put("like_boolean", true);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * 좋아요 클릭
	 * 
	 * @PostMapping("/board/good/{u_email}/{b_type}/{b_idx}") public
	 * ResponseEntity<HashMap<String, Object>> goodClick(@PathVariable("b_type") int
	 * b_type,
	 * 
	 * @PathVariable("b_idx") int b_idx, @PathVariable("u_email") String u_email) {
	 * try { BoardPK boardPK = new BoardPK(); boardPK.setB_type(b_type);
	 * boardPK.setB_idx(b_idx); boardPK.setU_email(u_email);
	 * 
	 * 
	 * 
	 * int like_count = service.goodCount(boardPK); HashMap<String, Object> map =
	 * new HashMap<>(); map.put("like_count", like_count); map.put("like_boolean",
	 * true);
	 * 
	 * return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK); }
	 * catch (Exception e) { return new ResponseEntity<>(null,
	 * HttpStatus.NOT_FOUND); } }
	 */

	/* 좋아요 클릭 */
	@ApiOperation("좋아요 클릭")
	@PostMapping("/board/good")
	public ResponseEntity<HashMap<String, Object>> goodClick(@RequestBody BoardPK boardPK) {
		try {

			boolean flag = service.goodClick(boardPK);

			HashMap<String, Object> map = new HashMap<>();

			map.put("like_boolean", flag);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

////	좋아요 취소 
//	@GetMapping("/board/goodCancel")
//	public ResponseEntity<HashMap<String, Object>> goodClickCancel() {
//		// 임시정보 취소했다고 가정 ->
//		int b_idx = 1;// (김호중 기사)
//		String userEmail = "a1234";
//
//		if (service.goodClickCancel(b_idx)) {
//			if (service.goodAuserConnectDelete(b_idx, userEmail)) {
////				int like_count = service.goodCount(b_idx);
//				int like_count = 1;
//				HashMap<String, Object> map = new HashMap<>();
//				map.put("like_count", like_count);
//				map.put("like_boolean", false);
//				return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//			}
//		} else {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//	}

	/* 좋아요 취소 */
	@ApiOperation("좋아요 취소")
	@DeleteMapping("/board/good/{b_type}/{b_idx}/{u_email}")
	public ResponseEntity<HashMap<String, Object>> goodClickCancel(@PathVariable("b_type") int b_type,
			@PathVariable("b_idx") int b_idx, @PathVariable("u_email") String u_email) {
		try {
			BoardPK boardPK = new BoardPK();
			boardPK.setB_type(b_type);
			boardPK.setB_idx(b_idx);
			boardPK.setU_email(u_email);
			boolean flag = service.goodClickCancel(boardPK);

			HashMap<String, Object> map = new HashMap<>();

			map.put("like_boolean", flag);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

//	임시로 팔로우 넣어줌 
	@GetMapping("/follow/apply")
	public ResponseEntity<String> Follow() {
		// 임영웅 꺼를 눌렀다고 가정
		// 유저 아이디가 a1234 라고 가정
		// favorite 테이블에 입력
		FollowDto dto = new FollowDto();
		dto.setU_email("a1234");
		dto.setS_idx(1);

		if (service.followApply(dto)) {
			return new ResponseEntity<>("팔로우 추가 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("팔로우 추가 실패", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/follow/delete")
	public Object FollowDelete() {
		// 유저 아이디가 a1234 라고 가정
		// favorite 테이블에서 삭제
		if (service.followDelete("a1234")) {
			return new ResponseEntity<>("팔로우 삭제 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("팔로우 삭제 실패", HttpStatus.NOT_FOUND);
		}
	}
//	임시로 팔로우 넣어줌. 

	@GetMapping("/board/ArticleAllList")
	public ResponseEntity<List<ArticleDto>> ArticleAllList() {
		List<ArticleDto> list = service.articleAllList();
		if (list != null) {
			return new ResponseEntity<List<ArticleDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/board/ArticleSearchList")
	public ResponseEntity<List<ArticleDto>> ArticleSearchList(@RequestParam int s_idx) {
		List<ArticleDto> list = service.articleSearchList(s_idx);
		if (list != null) {
			return new ResponseEntity<List<ArticleDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/board/VideoAllList")
	public ResponseEntity<List<VideoDto>> VideoAllList() {
		List<VideoDto> list = service.videoAllList();
		if (list != null) {
			return new ResponseEntity<List<VideoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/board/VideoSearchList")
	public ResponseEntity<List<VideoDto>> VideoSearchList(@RequestParam int s_idx) {
		List<VideoDto> list = service.videoSearchList(s_idx);
		if (list != null) {
			return new ResponseEntity<List<VideoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
