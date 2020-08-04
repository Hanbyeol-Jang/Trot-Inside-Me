package com.web.curation.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.SearchDao;
import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	SearchDao searchDao;

	static Map<String, String> m = new HashMap<String, String>() {
		{
			put("Jan", "01");
			put("Feb", "02");
			put("Mar", "03");
			put("Apr", "04");
			put("May", "05");
			put("Jun", "06");
			put("Jul", "07");
			put("Aug", "08");
			put("Sep", "09");
			put("Oct", "10");
			put("Nov", "11");
			put("Dec", "12");
		}
	};

	@Override
	public List<SingerDto> singerAllList() {
		return searchDao.singerAllList();
	}

	@Override
	public SingerDto singerSearch(int s_idx) {
		return searchDao.singerSearch(s_idx);
	}

	// 네이버 동영상 DB에 넣기
	@Override
	public void insertVideo(String s_name) {
		try {

			String url = "https://tv.naver.com/search?query=" + s_name;
			Document doc = Jsoup.connect(url).get();
			Elements elem = doc.select("div.my_mv");

			for (Element e : elem.select("div.thl_a")) {
				// vno = video number
				String vno = e.select("a").attr("href");
				int end = vno.indexOf("?");
				vno = vno.substring(3, end);

				String vsrc = "https://tv.naver.com/embed/" + vno + "?autoPlay=true";
				if (isUrl(vno, s_name)) {
					System.out.println(s_name + "에 대한 " + vno + "가  DB에 있습니다.");
					continue;
				} else {
					System.out.println(vno + "가  DB에 없습니다.");
					BoardDto boardDto = new BoardDto();
					boardDto.setB_thumbnail(e.select("a img").attr("src")); // 썸네일
					boardDto.setB_title(e.select("a").attr("title")); // 제목
					boardDto.setB_url(vsrc); // url
					boardDto.setB_member(s_name); // 가수이름

					String contentUrl = "https://tv.naver.com/v/";
					Document doc2 = Jsoup.connect(contentUrl + vno).get();
					String desc = doc2.select("p.desc").text();
					String b_date = doc2.select("span.date").text();
					boardDto.setB_date(b_date.substring(2, 6) + "년 " + b_date.substring(7, 9) + "월 "
							+ b_date.substring(10, 12) + "일");
					boardDto.setB_content(desc);
					searchDao.insertVideo(boardDto);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 네이버 기사 DB에 넣기
	@Override
	public void insertArticle(String s_name) {

		try {
			String url = "http://newssearch.naver.com/search.naver?where=rss&query=" + s_name;
			Document doc = Jsoup.connect(url).get();

			Elements elem = doc.select("channel");
			for (Element e : elem.select("item")) {
				if (isUrl2(e.select("link").text(), s_name)) {
					System.out.println(s_name + "에 대한 " + e.select("link").text() + "가  DB에 있습니다.");
				} else {
					String b_date = e.select("pubDate").text();
//					System.out.println(b_date.substring(12, 16)+"년 " + m.get(b_date.substring(8,11))+"월 "+b_date.substring(5,7)+"일");
					BoardDto boardDto = new BoardDto();
					boardDto.setB_title(e.select("title").text());
					boardDto.setB_url(e.select("link").text());
					boardDto.setB_member(s_name);
					boardDto.setB_company(e.select("author").text());
					boardDto.setB_date(b_date.substring(12, 16) + "년 " + m.get(b_date.substring(8, 11)) + "월 "
							+ b_date.substring(5, 7) + "일");
					boardDto.setB_thumbnail(e.select("category").next().attr("url"));
					searchDao.insertArticle(boardDto);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 네이버 동영상 전체 리스트 출력
	@Override
	public List<BoardDto> selectVideoList(String s_name) {
		return searchDao.selectVideoList(s_name);
	}

	// 네이버 기사 전체 리스트 출력
	@Override
	public List<BoardDto> selectArticleList(String s_name) {
		return searchDao.selectArticleList(s_name);
	}

	// 네이버 동영상 db에 있는지 확인
	@Override
	public boolean isUrl(String vno, String name) {

		return searchDao.isUrl(vno, name);
	}

	// 네이버 기사db에 있는지 확인
	@Override
	public boolean isUrl2(String url, String name) {

		return searchDao.isUrl2(url, name);
	}

	// 해당 가수 영상 디테일
	@Override
	public BoardDto videoDetail(int s_idx, int b_idx) {
		return searchDao.videoDetail(s_idx, b_idx);
	}

	//편성표 전체 출력
	@Override
	public List<BroadCastingDto> broadCastAllList() {
		return searchDao.broadCastAllList();
	}

}
