package com.web.curation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.web.curation.dao.SearchDao;
import com.web.curation.dto.BoardDto;
import com.web.curation.service.SearchService;

@Service
public class YoutubeAPI {
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private SearchService searchService;
	
	@Value("${YOUTUBE_API_KEY}")
	private String API_KEY;
	
	public String search(String search) throws IOException {
		String apiurl = "https://www.googleapis.com/youtube/v3/search";
		apiurl += "?key="+API_KEY;
		apiurl += "&part=snippet&type=video&maxResults=2&videoEmbeddable=true";
		apiurl += "&q="+URLEncoder.encode(search,"UTF-8");
		URL url = new URL(apiurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

		String line = "";
		String result = "";

		while ((line = br.readLine()) != null) {
			result += line;
		}
//		System.out.println("response body : " + result);

		// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성		
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = (JsonObject)parser.parse(result);
		JsonArray itemArray = (JsonArray) jsonObj.get("items");
		BoardDto boardDto = new BoardDto();
		for (JsonElement e : itemArray) {
			String vno = e.getAsJsonObject().get("id").getAsJsonObject().get("videoId").getAsString();
			if (searchService.isUrl(vno, search)) {
//				System.out.println(search + "에 대한 " + vno + "가  DB에 있습니다.");
				continue;
			} else {
			boardDto.setB_url("https://www.youtube.com/embed/"+e.getAsJsonObject().get("id").getAsJsonObject().get("videoId").getAsString());
			JsonObject snippet = (JsonObject) e.getAsJsonObject().get("snippet");
			String str = snippet.getAsJsonObject().get("publishTime").getAsString();
			boardDto.setB_member(search);
			boardDto.setB_title(snippet.getAsJsonObject().get("title").getAsString());
			boardDto.setB_content(snippet.getAsJsonObject().get("description").getAsString());
			boardDto.setB_thumbnail(snippet.getAsJsonObject().get("thumbnails").getAsJsonObject().get("high").getAsJsonObject().get("url").getAsString());
			boardDto.setB_date(str.substring(0,4)+"년 "+str.substring(5,7)+"월 "+str.substring(8,10)+"일");
			searchDao.insertVideo(boardDto);

		}
		}
		br.close();
		return result.toString();
	}
}