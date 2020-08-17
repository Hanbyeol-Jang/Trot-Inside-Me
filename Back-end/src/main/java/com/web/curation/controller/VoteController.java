package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.TopDto;
import com.web.curation.dto.VoteDto;
import com.web.curation.service.VoteService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class VoteController {

	@Autowired
	private VoteService voteService;

	/* 투표하기 */
	@ApiOperation("투표하기")
	@PostMapping("/vote")
	public ResponseEntity<HashMap<String, Object>> voteClick(@RequestBody VoteDto voteDto) {
		try {
			boolean flag = voteService.voteClick(voteDto);
			HashMap<String, Object> map = new HashMap<>();
			map.put("vote_boolean", flag);
			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/* 투표하기 */
	@ApiOperation("투표했는지 확인")
	@GetMapping("/voteCheck/{u_email}")
	public ResponseEntity<HashMap<String, Object>> voteCheck(@PathVariable String u_email) {
		try {
			boolean flag = voteService.voteCheck(u_email);
			HashMap<String, Object> map = new HashMap<>();
			map.put("hasVote", flag);
			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation("해당 가수 투표 현황 확인")
	@GetMapping("/voteOverview/{s_idx}")
	public ResponseEntity<HashMap<String, Object>> voteOverview(@PathVariable int s_idx) {
		try {
			String s_name = voteService.idxToName(s_idx);
			String s_img = voteService.singerImg(s_idx);
			int thisWeek = voteService.thisWeek(s_idx);
			int thisWeekTotal = voteService.thisWeekTotal(s_idx);
			int lastWeek = voteService.lastWeek(s_name);
			int lastWeekTotal = voteService.lastWeekTotal(s_name);


			double thisWeekRatio = Math.round(((double) thisWeek / (double) thisWeekTotal * 1000)) / 100.0;
			double lastWeekRatio = Math.round(((double) lastWeek / (double) lastWeekTotal * 1000)) / 100.0;


			int thisWeekRank = voteService.thisWeekRank(s_idx);
			int lastWeekRank = voteService.lastWeekRank(s_name);

			HashMap<String, Object> map = new HashMap<>();

			map.put("s_name", s_name);
			map.put("s_img", s_img);
			map.put("thisWeek", thisWeek);
			map.put("thisWeekTotal", thisWeekTotal);
			map.put("lastWeek", lastWeek);
			map.put("lastWeekTotal", lastWeekTotal);

			map.put("thisWeekRatio", thisWeekRatio);
			map.put("lastWeekRatio", lastWeekRatio);

			map.put("thisWeekRank", thisWeekRank);
			map.put("lastWeekRank", lastWeekRank);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation("이번주 top 3")
	@GetMapping("/ThisWeekTopThree")
	public ResponseEntity<List<TopDto>> thisWeekTopThree() {
		try {
			List<TopDto> list = voteService.thisWeekTopThree();

			return new ResponseEntity<List<TopDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation("지난주 top 3")
	@GetMapping("/lastWeekTopThree")
	public ResponseEntity<List<TopDto>> lastWeekTopThree() {
		try {
			List<TopDto> list = voteService.lastWeekTopThree();

			return new ResponseEntity<List<TopDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
