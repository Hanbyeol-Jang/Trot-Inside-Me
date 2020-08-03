package com.web.curation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebCurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCurationApplication.class, args);
	}
	
//	 카카오 통신
//	@Bean
//	public ReesTemplate getRestTemplate() {
//		return new RestTemplate();
//	}

}
