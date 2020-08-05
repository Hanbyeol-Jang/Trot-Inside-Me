package com.web.curation.controller;

import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.BoardPK;
import com.web.curation.dto.ReplyDto;
import com.web.curation.service.BoardService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	/* 좋아요 수 표시 */
	@ApiOperation("좋아요 수 표시")
	@GetMapping("/board/good/{b_type}/{b_idx}")
	public ResponseEntity<HashMap<String, Object>> goodCount(@PathVariable("b_type") int b_type,
			@PathVariable("b_idx") int b_idx) {

		try {
			BoardPK boardPK = new BoardPK();
			boardPK.setB_type(b_type);
			boardPK.setB_idx(b_idx);

			int like_count = boardService.goodCount(boardPK);
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

			boolean flag = boardService.goodClick(boardPK);

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
			boolean flag = boardService.goodClickCancel(boardPK);

			HashMap<String, Object> map = new HashMap<>();

			map.put("like_boolean", flag);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/* 댓글 작성 */
	@ApiOperation("댓글 작성")
	@PostMapping("/board/reply")
	public ResponseEntity<HashMap<String, Object>> addComment(@RequestBody ReplyDto replyDto) {
		try {

			boolean flag = boardService.addComment(replyDto);

			HashMap<String, Object> map = new HashMap<>();

			map.put("reply_boolean", flag);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/* 댓글 작성 */
	@ApiOperation("댓글 삭제")
	@DeleteMapping("/board/reply/{r_idx}/{b_type}/{b_idx}/{u_email}")
	public ResponseEntity<HashMap<String, Object>> deleteComment(@PathVariable("r_idx") int r_idx,
			@PathVariable("b_type") int b_type, @PathVariable("b_idx") int b_idx,
			@PathVariable("u_email") String u_email) {
		try {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setR_idx(r_idx);
			replyDto.setB_type(b_type);
			replyDto.setB_idx(b_idx);
			replyDto.setU_email(u_email);
			boolean flag = boardService.deleteComment(replyDto);

			HashMap<String, Object> map = new HashMap<>();

			map.put("like_boolean", flag);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
