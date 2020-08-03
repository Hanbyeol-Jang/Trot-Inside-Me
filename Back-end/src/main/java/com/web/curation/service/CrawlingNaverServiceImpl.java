package com.web.curation.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.CrawlingNaverDao;
import com.web.curation.dto.ArticleDto;
import com.web.curation.dto.UserDto;
import com.web.curation.dto.VideoDto;

@Service
public class CrawlingNaverServiceImpl implements CrawlingNaverService {

	@Autowired
	private CrawlingNaverDao cnd;

	//Naver Videos 
	@Override
	public void videoUrl(String name) {
		try {
			String url = "https://tv.naver.com/search?query="+name+"&page=1";
			Document doc = Jsoup.connect(url).get();
			Elements elem = doc.select("div.my_mv");
			
			for (Element e : elem.select("dt")) {
				int end = e.select("a").attr("href").indexOf("?");
				String vno  = e.select("a").attr("href");
				int qm = vno.indexOf("?");
				vno = vno.substring(3, qm);
				String pv = "https://tv.naver.com/embed/"+vno+"?autoPlay=true";
				VideoDto videoDto = new VideoDto();
				videoDto.setV_title(e.select("a").attr("title"));
				videoDto.setV_url(pv);
				videoDto.setV_member(name);
				System.out.println(videoDto.toString());
				cnd.insertVideo(videoDto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Naver News
	@Override
	public void newsUrl(String keyword) {
		
		
		try {
			String url = "http://newssearch.naver.com/search.naver?where=rss&query="+keyword;
			Document doc = Jsoup.connect(url).get();

			Elements elem = doc.select("channel");
			for (Element e : elem.select("item")) {
				ArticleDto articleDto = new ArticleDto();
				articleDto.setA_title(e.select("title").text());
				articleDto.setA_url(e.select("link").text());
				articleDto.setA_member(keyword);
				System.out.println(articleDto.toString());
				cnd.insertArticle(articleDto);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//item -> title & link
		
//		<description>
//		<![CDATA[ 임영웅 모친의 미용실까지 찾을정도로 임영웅의 찐팬인 배우 김영옥이 임영웅을 만나 행복한 시간을 가졌다. 임영웅의 찐팬이라고 밝혔던 배우 김영옥이 임영웅과 만났다. 김영옥은 최근 ‘스타 다큐 마이웨이’의 촬영을... ]]>
//		</description>
//		<pubDate>Mon, 27 Jul 2020 11:54:00 +0900</pubDate>
//		<author>여성조선</author>
//		<category>섹션없음</category>
//		<media:thumbnail url="https://imgnews.pstatic.net/image/thumb140/5238/2020/07/27/11453.jpg"/>
	}

	@Override
	public List<VideoDto> selectVideoList(VideoDto videoDto) {
		return cnd.selectVideoList(videoDto);
	}

}
