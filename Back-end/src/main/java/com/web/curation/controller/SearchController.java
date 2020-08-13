package com.web.curation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.service.SearchService;
import com.web.curation.util.YoutubeAPI;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class SearchController {

	@Autowired
	private SearchService searchService;
	@Autowired
	private YoutubeAPI youtubeAPI;
	// 가수 리스트
	@GetMapping("/singer")
	@ApiOperation(value = "가수 리스트")
	public ResponseEntity<List<SingerDto>> singerList() {
		List<SingerDto> list = searchService.singerAllList();
		if (list != null) {
			return new ResponseEntity<List<SingerDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 가수 디테일
	@GetMapping("/singer/{s_idx}")
	@ApiOperation(value = "가수 디테일")
	public ResponseEntity<SingerDto> singerDetail(@RequestParam int s_idx) {
		SingerDto singerDto = searchService.singerSearch(s_idx);
		if (singerDto != null) {
			return new ResponseEntity<SingerDto>(singerDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 해당 가수 영상 리스트
	@GetMapping("/singer/{s_idx}/videos")
	@ApiOperation(value = "가수로 검색 비디오")
	public ResponseEntity<List<BoardDto>> singerVideoList(@PathVariable int s_idx, @RequestParam int page) {
		List<BoardDto> list = null;
		List<BoardDto> showList = new LinkedList<BoardDto>();
		// s_idx로 가수 이름 검색
		SingerDto singerDto = searchService.singerSearch(s_idx);

		if (page == 1) {
			// 크롤링 후 디비 저장
			searchService.insertVideo(singerDto.getS_name());
		}
		list = searchService.selectVideoList(singerDto.getS_name());
//		page = 5 * page - 5;
//		for (int i = page; i < page + 5; i++) {
//			System.out.println(list.get(i));
//			showList.add(list.get(i));
//		}
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
	@GetMapping("singer/{s_idx}/articles")
	@ApiOperation(value = "가수로 검색 기사 ")
	public ResponseEntity<List<BoardDto>> searchNews(@PathVariable("s_idx") int s_idx, @RequestParam int page) {
		List<BoardDto> list = null;
		List<BoardDto> showList = new LinkedList<BoardDto>();
		// s_idx로 가수 이름 검색
		SingerDto singerDto = searchService.singerSearch(s_idx);

		if (page == 1) {
			// 네이버 기사 검색 후 디비에 넣기
			searchService.insertArticle(singerDto.getS_name());
		}
		list = searchService.selectArticleList(singerDto.getS_name());
//		page = 5 * page - 5;
//
//		// 5개씩 보여주기
//		for (int i = page; i < page + 5; i++) {
//			showList.add(list.get(i));
//		}

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

	@GetMapping("singer/videos/{b_idx}")
	@ApiOperation(value = "영상 디테일")
	public ResponseEntity<BoardDto> videoDetail(@PathVariable int b_idx) {
		BoardDto boardDto = searchService.videoDetail(b_idx);
		if (boardDto != null) {
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("singer/articles/{b_idx}")
	@ApiOperation(value = "기사 디테일")
	public ResponseEntity<BoardDto> articleDetail(@PathVariable int b_idx) {
		BoardDto boardDto = searchService.articleDetail(b_idx);
		if (boardDto != null) {
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}



	@GetMapping("/schedule/todayList")
	public ResponseEntity<List<BroadCastingDto>> todaylist() {
		List<BroadCastingDto> list = searchService.broadCastAllList();
		if (list != null) {
			return new ResponseEntity<List<BroadCastingDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/schedule/singerScheduleList/{s_idx}")
	public ResponseEntity<List<BroadCastingDto>> singerScheduleList(@PathVariable("s_idx") int s_idx) {
		SingerDto singerDto = searchService.singerSearch(s_idx);
		List<BroadCastingDto> list = searchService.singerScheduleList(singerDto.getS_name());
		if (list != null) {
			return new ResponseEntity<List<BroadCastingDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// #### Board에 있는 영상 전체 개수
	// #### Board에 있는 기사 전체 개수
	// #### Board에 있는 가수별 영상 개수
	// #### Board에 있는 가수별 기사 개수
	@GetMapping("/board/count")
	@ApiOperation(value = "b_type 1 = 동영상, 2 = 기사 && s_idx 0 = 전체, 나머지는 숫자 입력")
	public ResponseEntity<Integer> checklist(@RequestParam int b_type, @RequestParam int s_idx) {
		if (b_type == 1 || b_type == 2) {
			int boardDto = searchService.checklist(b_type, s_idx);
			return new ResponseEntity<Integer>(boardDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
		}

	}
//	Board에 있는 좋아요순 (전체 영상, 전체 기사에 대한 좋아요순)

	@GetMapping("{b_type}/good")
	@ApiOperation(value = "영상 좋아요순으로 정렬")
	public ResponseEntity<List<BoardDto>> videogood(@PathVariable int b_type, @RequestParam int page) {
		List<BoardDto> showList = new LinkedList<BoardDto>();
		if (b_type == 1 || b_type == 2) {
			List<BoardDto> list = searchService.videoArticleGood(b_type);
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
	// Youtube Search
	@GetMapping("/search/youtube")
	@ApiOperation(value = "유튜브 검색")
	public ResponseEntity<String> youtubesearch(@RequestParam String keyword) {
		String result = "";
		try {
			System.out.println("실행");
			result = youtubeAPI.search(keyword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(result,HttpStatus.OK);
		
	}
}
