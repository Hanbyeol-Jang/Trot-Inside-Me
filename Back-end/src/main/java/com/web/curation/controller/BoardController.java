package com.web.curation.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.BoardPK;
import com.web.curation.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class BoardController {

	@Autowired
	private BoardService service;

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

}
