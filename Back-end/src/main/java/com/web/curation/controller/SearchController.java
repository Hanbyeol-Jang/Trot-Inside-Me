package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.SingerDto;
import com.web.curation.service.SearchService;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class SearchController {
	
	@Autowired
	SearchService service;
	
	@GetMapping("/search/singerlist")
	public ResponseEntity<List<SingerDto>> singerlist() {
		List<SingerDto> list =service.singerAllList();
		if (list!=null) {
			return  new ResponseEntity<List<SingerDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/search/singerSearch")
	public ResponseEntity<SingerDto> singerSearch(@RequestParam int s_idx) {
		SingerDto dto =service.singerSearch(s_idx);
		if (dto!=null) {
			return  new ResponseEntity<SingerDto>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
