package com.web.curation.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping("/voteCheck/{u_email}/{u_hasVote}")
	public ResponseEntity<HashMap<String, Object>> voteCheck(@PathVariable String u_email) {
		try {

			boolean flag = voteService.voteCheck(u_email);

			HashMap<String, Object> map = new HashMap<>();

			map.put("hasVote?", flag);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
