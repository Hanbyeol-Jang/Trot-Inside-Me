package com.web.curation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.ReplyDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.dto.UserDto;
import com.web.curation.service.BoardService;
import com.web.curation.service.TimeService;
import com.web.curation.service.UserService;
import com.web.curation.util.KakaoAPI;
import com.web.curation.util.YoutubeAPI;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private KakaoAPI kakaoAPI;
	@Autowired
	private UserService userService;
	@Autowired
	private TimeService timeService;
	@Autowired
	private YoutubeAPI youtubeAPI;

	//메인 가수 리스트
	@GetMapping("/singerlist")
	@ApiOperation(value = "메인 가수 리스트")
	public ResponseEntity<List<SingerDto>> singerList() {
		List<SingerDto> list = boardService.singerAllList();
		if (list != null) {
			list.remove(0);
			return new ResponseEntity<List<SingerDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	
	//메인 가수 디테일
	@GetMapping("/singerdetail/{s_idx}")
	@ApiOperation(value = "메인 가수 디테일")
	public ResponseEntity<SingerDto> singerDetail(@PathVariable int s_idx, HttpServletRequest request) {
		String token = request.getHeader("token");
		GoodDto dto = new GoodDto();
		dto.setB_idx(s_idx);
		if (!token.equals("null")) {// 회원
			dto.setU_email(userService.getTokenInfo(request).getU_email());
		}
		SingerDto singerDto = boardService.singerDetail(dto);
		if (singerDto != null) {
			return new ResponseEntity<SingerDto>(singerDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	

	//메인 해당 영상/기사 리스트 - 좋아요
	@GetMapping("/mainlist/{b_type}")
	@ApiOperation(value = "메인 기사, 영상 리스트 ")
	public ResponseEntity<List<BoardPK>> mainList(@PathVariable int b_type, @RequestParam int page) {
		List<BoardPK> showList = new ArrayList<BoardPK>();
		List<BoardPK> list = boardService.mainlist(b_type);
		int lastPageRemain = list.size() % 5;
		int lastPage = list.size() - lastPageRemain;
		page = 5 * page - 5;
		// 5개씩 보여주기
		if (page < lastPage) {
			for (int i = page; i < page + 5; i++) {
				showList.add(list.get(i));
				list.get(i).setB_cnt(list.size());
			}
		} else if (page == lastPage) {
			for (int i = page; i < page + lastPageRemain; i++) {
				showList.add(list.get(i));
				list.get(i).setB_cnt(list.size());
			}
		}
		return new ResponseEntity<List<BoardPK>>(showList, HttpStatus.OK);
	}

	//메인 디테일
	@GetMapping("/detail/{b_type}/{b_idx}")
	@ApiOperation(value = "메인 영상/기사  디테일 ")
	public ResponseEntity<BoardPK> videoDetail(@PathVariable("b_type") int b_type, @PathVariable("b_idx") int b_idx,
			HttpServletRequest request) {
		String token = request.getHeader("token");
		GoodDto dto = new GoodDto();
		dto.setB_idx(b_idx);
		dto.setB_type(b_type);
		if (!token.equals("null")) {// 회원
			UserDto udto = userService.getTokenInfo(request);
			if (udto.getU_name().equals("F")) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			dto.setU_email(udto.getU_email());
		}
		BoardPK boardDto = boardService.detail(dto);
		if (boardDto != null) {
			return new ResponseEntity<BoardPK>(boardDto, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	
	
	// 해당 가수 영상 리스트
	@GetMapping("/videolist/{s_idx}")
	@ApiOperation(value = "가수로 검색 비디오")
	public ResponseEntity<List<BoardPK>> singerList(@PathVariable int s_idx, @RequestParam int page) {
		List<BoardPK> list = null;
		List<BoardPK> showList = new LinkedList<BoardPK>();
		// s_idx로 가수 이름 검색
		SingerDto singerDto = boardService.singerSearch(s_idx);
		if(singerDto==null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

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
				showList.add(list.get(i));
				list.get(i).setB_cnt(list.size());
			}
		} else if (page == lastPage) {
			for (int i = page; i < page + lastPageRemain; i++) {
				showList.add(list.get(i));
				list.get(i).setB_cnt(list.size());
			}
		}

		if (showList != null) {
			return new ResponseEntity<List<BoardPK>>(showList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	// 해당 가수 영상 리스트
		@GetMapping("/videolist/search")
		@ApiOperation(value = "영상 검색 기능")
		public ResponseEntity<List<BoardPK>> searchVideo(@RequestParam String word, @RequestParam int page) {
			List<BoardPK> list = null;
			List<BoardPK> showList = new LinkedList<BoardPK>();
			// 키워드로 영상 검색
			
			list = boardService.searchVideo(word);
			// 최신순 , 가수 같이 검색
			int lastPageRemain = list.size() % 5;
			int lastPage = list.size() - lastPageRemain;
			page = 5 * page - 5;
			
			// 5개씩 보여주기
			if (page < lastPage) {
				for (int i = page; i < page + 5; i++) {
					showList.add(list.get(i));
					list.get(i).setB_cnt(list.size());
				}
			} else if (page == lastPage) {
				for (int i = page; i < page + lastPageRemain; i++) {
					showList.add(list.get(i));
					list.get(i).setB_cnt(list.size());
				}
			}
			

			if (showList != null) {
				return new ResponseEntity<List<BoardPK>>(showList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		}
	// 해당 가수 기사 리스트
	@GetMapping("/articlelist/{s_idx}")
	@ApiOperation(value = "가수로 검색 기사 ")
	public ResponseEntity<List<BoardPK>> searchNews(@PathVariable("s_idx") int s_idx, @RequestParam int page) {
		List<BoardPK> list = null;
		List<BoardPK> showList = new LinkedList<BoardPK>();
		SingerDto singerDto = boardService.singerSearch(s_idx);
		if(singerDto==null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
				showList.add(list.get(i));
			}
		} else if (page == lastPage) {
			for (int i = page; i < page + lastPageRemain; i++) {
				showList.add(list.get(i));
			}
		}

		if (showList != null) {
			return new ResponseEntity<List<BoardPK>>(showList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}


	@GetMapping("/replylist/{b_type}/{b_idx}")
	@ApiOperation(value = "댓글리스트  ")
	public ResponseEntity<List<ReplyDto>> replylist(@PathVariable("b_type") int b_type, @RequestParam("page") int page,
			@PathVariable("b_idx") int b_idx) {
		GoodDto dto = new GoodDto();
		dto.setB_idx(b_idx);
		dto.setB_type(b_type);
		List<ReplyDto> list = boardService.replylist(dto);
		if (list != null) {
			List<ReplyDto> showList = new ArrayList<>();
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
			return new ResponseEntity<List<ReplyDto>>(showList, HttpStatus.OK);

		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	/* 댓글 작성 */
	@ApiOperation("댓글 작성")
	@PostMapping("/replyadd")
	public ResponseEntity<List<ReplyDto>> replyadd(@RequestBody ReplyDto rDto, @RequestParam("page") int page,
			HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			rDto.setR_email(udto.getU_email());
			if (boardService.addComment(rDto)) {
				GoodDto dto = new GoodDto();
				dto.setB_idx(rDto.getB_idx());
				dto.setB_type(rDto.getB_type());
				dto.setU_email(udto.getU_email());
				List<ReplyDto> list = boardService.replylist(dto);
				if (list != null) {
					List<ReplyDto> showList = new ArrayList<>();
					int lastPageRemain = list.size() % 5;
					int lastPage = list.size() - lastPageRemain;
					page = 5 * page - 5;
					// 5개씩 보여주기
					if (page < lastPage) {
						for (int i = 0; i < page + 5; i++) {
							showList.add(list.get(i));
						}
					} else if (page == lastPage) {
						for (int i = 0; i < page + lastPageRemain; i++) {
							showList.add(list.get(i));
						}
					} else {
						return new ResponseEntity<List<ReplyDto>>(list, HttpStatus.OK);
					}
					return new ResponseEntity<List<ReplyDto>>(showList, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	/* 댓글 작성 */
	@ApiOperation("댓글 삭제")
	@DeleteMapping("/replydelete/{b_type}/{b_idx}/{r_idx}")
	public ResponseEntity<List<ReplyDto>> deleteComment(@PathVariable("b_type") int b_type,
			@PathVariable("b_idx") int b_idx, @PathVariable("r_idx") int r_idx, @RequestParam("page") int page,
			HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			GoodDto rDto = new GoodDto();
			rDto.setR_idx(r_idx);
			rDto.setB_type(b_type);
			rDto.setB_idx(b_idx);
			rDto.setU_email(udto.getU_email());
			if (boardService.deleteComment(rDto)) {
				List<ReplyDto> list = boardService.replylist(rDto);
				List<ReplyDto> showList = new ArrayList<>();
				if (list != null) {
					int lastPageRemain = list.size() % 5;
					int lastPage = list.size() - lastPageRemain;
					page = 5 * page - 5;
					// 5개씩 보여주기
					if (page < lastPage) {
						for (int i = 0; i < page + 5; i++) {
							showList.add(list.get(i));
						}
					} else if (page == lastPage) {
						for (int i = 0; i < page + lastPageRemain; i++) {
							showList.add(list.get(i));
						}
					} else {
						return new ResponseEntity<List<ReplyDto>>(list, HttpStatus.OK);
					}
					return new ResponseEntity<List<ReplyDto>>(showList, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	/* 좋아요 클릭 */
	@ApiOperation("좋아요 클릭")
	@GetMapping("/good/{b_type}/{b_idx}")
	public ResponseEntity<Integer> goodClick(@PathVariable("b_type") int b_type, @PathVariable("b_idx") int b_idx,
			@RequestParam int isgood, HttpServletRequest request) {
		UserDto udto = userService.getTokenInfo(request);
		if (udto.getU_name().equals("F")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			GoodDto dto = new GoodDto();
			dto.setB_idx(b_idx);
			dto.setB_type(b_type);
			dto.setU_email(udto.getU_email());
			if (isgood == 1) { // 좋아요 취소
				if (boardService.goodClickCancel(dto)) {
					isgood = 0;
				}
			} else { // 좋아요 선택
				if (boardService.goodClick(dto)) {
					isgood = 1;
				}
			}
			return new ResponseEntity<Integer>(isgood, HttpStatus.OK);

		}
	}

	//편성표 리스트
	@ApiOperation("편성표 리스트 ")
	@GetMapping("/schedule/todayList")
	public ResponseEntity<List<BroadCastingDto>> todaylist() {
		List<BroadCastingDto> list = boardService.broadCastAllList();
		if (list != null) {
			return new ResponseEntity<List<BroadCastingDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	//가수 스케줄 리스트
	@ApiOperation("가수 스케줄 리스트 ")
	@GetMapping("/schedule/singerScheduleList/{s_idx}")
	public ResponseEntity<List<BroadCastingDto>> singerScheduleList(@PathVariable("s_idx") int s_idx) {
		List<BroadCastingDto> list = boardService.singerScheduleList(s_idx);
		if (list != null) {
			return new ResponseEntity<List<BroadCastingDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 나에게 메세지 보내기 (알림)
	@GetMapping("/tvmsg/{bc_idx}")
	public ResponseEntity<String> selectBroadCasting(@PathVariable("bc_idx") int bc_idx, HttpServletRequest request) {
		String useremail = userService.getTokenInfo(request).getU_email();
		String accessToken = userService.getUserInfo(useremail).getU_accessToken();
		BroadCastingDto broadCastingDto = timeService.selectBroadCasting(bc_idx);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("broadCastingDto", broadCastingDto);
		map.put("accessToken", accessToken);
		try {
			kakaoAPI.messageForMe(map);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("FAIL", HttpStatus.NOT_FOUND);
		}
	}
}
