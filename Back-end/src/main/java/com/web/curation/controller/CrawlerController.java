package com.web.curation.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.curation.dto.VideoDto;
import com.web.curation.service.CrawlingNaverService;
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
public class CrawlerController {
	
	//videos
	@Autowired
	CrawlingNaverService cns;
	
	@GetMapping("/search/naver/{name}")
	public String searchN(@PathVariable("name") String name) throws SQLException {
		System.out.println("logger - " + "/search/naver");
//		String URL = "https://tv.naver.com/search?query="+name+"&page=1";
		cns.videoUrl(name);
		return "redirect:"+"https://tv.naver.com/search?query="+name+"&page=1";
	}
	

	@GetMapping("/select/naver/{name}")
	public ResponseEntity<List<VideoDto>> selectN(@PathVariable("name") String name) throws SQLException{
		System.out.println("logger - " + "/select/naver"+name);
		VideoDto videoDto = new VideoDto();
		videoDto.setV_member(name);
		List<VideoDto> list = cns.selectVideoList(videoDto);
		System.out.println(list);
		return new ResponseEntity<List<VideoDto>>(list,HttpStatus.OK);
		
	}


	
	//news
	@GetMapping("search/naver/news/{keyword}")
	public String searchNews(@PathVariable("keyword") String keyword) {
		String URL = "http://newssearch.naver.com/search.naver?where=rss&query="+keyword;
//		System.out.println(URL);
		cns.newsUrl(keyword);
		return "redirect:"+URL;
		
	}
 }
