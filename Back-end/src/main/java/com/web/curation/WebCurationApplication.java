package com.web.curation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebCurationApplication {

	public static void main(String[] args) {
		System.out.println("spring start ");
		SpringApplication.run(WebCurationApplication.class, args);
		
	}

}
