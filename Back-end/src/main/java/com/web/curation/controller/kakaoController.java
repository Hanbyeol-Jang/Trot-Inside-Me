package com.web.curation.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.curation.service.UserService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
public class kakaoController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/kakao/login")
	public String login() throws SQLException {
		System.out.println("logger - " + "/kakao/login");
		String URL = "https://kauth.kakao.com/oauth/authorize?client_id=78183e66919b34b25f731ea9f2d99f0e"
				+ "&redirect_uri=http://localhost:8080/login"
				+ "&response_type=code";
		System.out.println(URL);
		return "redirect:"+URL;
	}
	
}
