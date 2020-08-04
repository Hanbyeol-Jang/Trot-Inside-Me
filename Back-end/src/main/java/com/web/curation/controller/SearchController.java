package com.web.curation.controller;

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

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class SearchController {

	@Autowired
	private SearchService searchService;

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
	@ApiOperation(value = "가수 비디오")
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
		page = 5 * page - 5;
		for (int i = page; i < page + 5; i++) {
			System.out.println(list.get(i));
			showList.add(list.get(i));
		}
		System.out.println("logger - showList" + showList.toString());

		if (showList != null) {
			return new ResponseEntity<List<BoardDto>>(showList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("singer/{s_idx}/videos/{b_idx}")
	public ResponseEntity<BoardDto> videoDetail(@PathVariable int s_idx, @PathVariable int b_idx) {
		BoardDto boardDto = searchService.videoDetail(s_idx, b_idx);
		if (boardDto != null) {
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// 해당 가수 기사 리스트
	@GetMapping("singer/{s_idx}/articles")
	@ApiOperation(value = "가수 기사")
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
		page = 5 * page - 5;

		// 5개씩 보여주기
		for (int i = page; i < page + 5; i++) {
			showList.add(list.get(i));
		}
		if (showList != null) {
			return new ResponseEntity<List<BoardDto>>(showList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/schedule/todayList")
	public ResponseEntity<List<BroadCastingDto>> todaylist() {
		List<BroadCastingDto> list =searchService.broadCastAllList();
		if (list!=null) {
			return  new ResponseEntity<List<BroadCastingDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
}
