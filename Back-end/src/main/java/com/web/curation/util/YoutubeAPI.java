package com.web.curation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

@Service
public class YoutubeAPI {
	public String search(String search) throws IOException {
		System.out.println("hi");
		String apiurl = "https://www.googleapis.com/youtube/v3/search";
		apiurl += "?key=AIzaSyCkFtOp9mm-wsFwzt6TZmuejCWBqCNMzvE";
		apiurl += "&part=snippet&type=video&maxResults=20&videoEmbeddable=true";
		apiurl += "&q="+URLEncoder.encode(search,"UTF-8");
		System.out.println(apiurl);
		URL url = new URL(apiurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		
		return response.toString();
	}
}