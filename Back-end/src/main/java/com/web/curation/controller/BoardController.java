package com.web.curation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.ReplyDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.dto.UserDto;
import com.web.curation.service.BoardService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;
	
	// 가수 리스트
	@GetMapping("/singerlist")
	@ApiOperation(value = "가수 리스트")
	public ResponseEntity<List<SingerDto>> singerList() {
		List<SingerDto> list = boardService.singerAllList();
		if (list != null) {
			return new ResponseEntity<List<SingerDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 가수 디테일
	@GetMapping("/singerdetail/{s_idx}")
	@ApiOperation(value = "가수 디테일")
	public ResponseEntity<SingerDto> singerDetail(@RequestParam int s_idx) {
		SingerDto singerDto = boardService.singerSearch(s_idx);
		if (singerDto != null) {
			return new ResponseEntity<SingerDto>(singerDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 해당 가수 영상 리스트 - 최신순
	@GetMapping("/mainlist/{b_type}/{s_idx}")
	@ApiOperation(value = "가수로 검색 비디오")
	public ResponseEntity<List<BoardDto>> mainList(@PathVariable int b_type,
			@PathVariable int s_idx, @RequestParam int page) {
		List<BoardDto> showList = new LinkedList<BoardDto>();
		if (b_type == 1 || b_type == 2) {
			List<BoardDto> list = boardService.videoArticleGood(b_type);
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
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	// 해당 가수 영상 리스트
	@GetMapping("/{s_idx}/videolist")
	@ApiOperation(value = "가수로 검색 비디오")
	public ResponseEntity<List<BoardDto>> singerList(@PathVariable int s_idx,
			@RequestParam int page) {
		List<BoardDto> list = null;
		List<BoardDto> showList = new LinkedList<BoardDto>();
		// s_idx로 가수 이름 검색
		SingerDto singerDto = boardService.singerSearch(s_idx);

		if (page == 1) {
			// 크롤링 후 디비 저장
			try {
				boardService.insertVideo(singerDto.getS_name());
				youtubeAPI.search(singerDto.getS_name());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 최신순 , 가수 같이 검색
		list = boardService.selectVideoList(singerDto.getS_name());
		int lastPageRemain = list.size() % 5;
		int lastPage = list.size() - lastPageRemain;
		page = 5 * page - 5;
		// 5개씩 보여주기
		if (page < lastPage) {
			for (int i = page; i < page + 5; i++) {
				System.out.println("5개");
				showList.add(list.get(i));
			}
		} else if (page == lastPage) {
			System.out.println("나머지");
			for (int i = page; i < page + lastPageRemain; i++) {
				showList.add(list.get(i));
			}
		}

		if (showList != null) {
			return new ResponseEntity<List<BoardDto>>(showList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	// 해당 가수 기사 리스트
	@GetMapping("/{s_idx}/articlelist")
	@ApiOperation(value = "가수로 검색 기사 ")
	public ResponseEntity<List<BoardDto>> searchNews(@PathVariable("s_idx") int s_idx, @RequestParam int page) {
		List<BoardDto> list = null;
		List<BoardDto> showList = new LinkedList<BoardDto>();
		SingerDto singerDto = boardService.singerSearch(s_idx);

		if (page == 1) {
			// 네이버 기사 검색 후 디비에 넣기
			boardService.insertArticle(singerDto.getS_name());
		}
		list = boardService.selectArticleList(singerDto.getS_name());

		int lastPageRemain = list.size() % 5;
		int lastPage = list.size() - lastPageRemain;
		page = 5 * page - 5;
		// 5개씩 보여주기
		if (page < lastPage) {
			for (int i = page; i < page + 5; i++) {
				System.out.println("5개");
				showList.add(list.get(i));
			}
		} else if (page == lastPage) {
			System.out.println("나머지");
			for (int i = page; i < page + lastPageRemain; i++) {
				showList.add(list.get(i));
			}
		}

		if (showList != null) {
			return new ResponseEntity<List<BoardDto>>(showList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/videodetail/{b_idx}")
	@ApiOperation(value = "영상 ")
	public ResponseEntity<BoardDto> videoDetail(@PathVariable int b_idx) {
		BoardDto boardDto = boardService.videoDetail(b_idx);
		if (boardDto != null) {
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/articledetail/{b_idx}")
	@ApiOperation(value = "영상 ")
	public ResponseEntity<BoardDto> articleDetail(@PathVariable int b_idx) {
		BoardDto boardDto = boardService.videoDetail(b_idx);
		if (boardDto != null) {
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/replylist/{b_idx}")
	@ApiOperation(value = "영상 ")
	public ResponseEntity<List<BoardDto>> replylist(@PathVariable int b_idx) {
		List<BoardDto> boardDto = boardService.videoDetail(b_idx);
		if (boardDto != null) {
			return new ResponseEntity<List<BoardDto>>(boardDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/* 댓글 작성 */
	@ApiOperation("댓글 작성")
	@PostMapping("/replyadd")
	public ResponseEntity<HashMap<String, Object>> addComment(
			@RequestBody ReplyDto replyDto, @PathVariable("b_type") int b_type,
			@PathVariable("b_idx") int b_idx, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			
		}
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
	@DeleteMapping("/replydelete/{b_type}/{b_idx}")
	public ResponseEntity<HashMap<String, Object>> deleteComment(@PathVariable("b_type") int b_type,
			@PathVariable("b_idx") int b_idx, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			
		}
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
	
	/* 좋아요 클릭 */
	@ApiOperation("좋아요 클릭")
	@PostMapping("/good/{b_type}/{b_idx}")
	public ResponseEntity<HashMap<String, Object>> goodClick(@PathVariable("b_type") int b_type, 
			@PathVariable("b_idx") int b_idx, @RequestParam int isgood, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			
		}
		
		
		try {

			boolean flag = boardService.goodClick(boardPK);

			HashMap<String, Object> map = new HashMap<>();

			map.put("like_boolean", flag);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
//	
//	
//	
//	/* 좋아요 수 표시 */
//	@ApiOperation("좋아요 수 표시")
//	@GetMapping("/board/good/{b_type}/{b_idx}")
//	public ResponseEntity<HashMap<String, Object>> goodCount(@PathVariable("b_type") int b_type,
//			@PathVariable("b_idx") int b_idx) {
//
//		try {
//			BoardPK boardPK = new BoardPK();
//			boardPK.setB_type(b_type);
//			boardPK.setB_idx(b_idx);
//
//			int like_count = boardService.goodCount(boardPK);
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("like_count", like_count);
//			map.put("like_boolean", true);
//
//			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//	}
//
//
//
//	/* 좋아요 취소 */
//	@ApiOperation("좋아요 취소")
//	@DeleteMapping("/board/good/{b_type}/{b_idx}/{u_email}")
//	public ResponseEntity<HashMap<String, Object>> goodClickCancel(@PathVariable("b_type") int b_type,
//			@PathVariable("b_idx") int b_idx, @PathVariable("u_email") String u_email) {
//		try {
//			BoardPK boardPK = new BoardPK();
//			boardPK.setB_type(b_type);
//			boardPK.setB_idx(b_idx);
//			boardPK.setU_email(u_email);
//			boolean flag = boardService.goodClickCancel(boardPK);
//
//			HashMap<String, Object> map = new HashMap<>();
//
//			map.put("like_boolean", flag);
//
//			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//	}
//
//	/* 댓글 수 표시 */
//	@ApiOperation("댓글 수 표시")
//	@GetMapping("/board/reply/{b_type}/{b_idx}")
//	public ResponseEntity<HashMap<String, Object>> commentCount(@PathVariable("b_type") int b_type,
//			@PathVariable("b_idx") int b_idx) {
//
//		try {
//			BoardPK boardPK = new BoardPK();
//			boardPK.setB_type(b_type);
//			boardPK.setB_idx(b_idx);
//
//			int like_count = boardService.commentCount(boardPK);
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("comment_count", like_count);
//			map.put("comment_boolean", true);
//
//			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//	}


}
