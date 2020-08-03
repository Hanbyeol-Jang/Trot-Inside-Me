package com.web.curation.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.service.TimeService;

@Component
public class BroadCastingSchedule {
	static String[][] broad4 = { { "뽕숭아학당",
			"https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EB%BD%95%EC%88%AD%EC%95%84+%ED%95%99%EB%8B%B9+%ED%8E%B8%EC%84%B1%ED%91%9C" },
			{ "신청곡을 불러드립니다 - 사랑의 콜센타",
					"https://search.naver.com/search.naver?where=nexearch&query=%EC%82%AC%EB%9E%91%EC%9D%98+%EC%BD%9C%EC%84%BC%ED%84%B0+%ED%8E%B8%EC%84%B1%ED%91%9C&sm=tab_stc" },
			{ "트롯신이 떴다",
					"https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&pkid=57&os=13857215&query=%ED%8A%B8%EB%A1%AF%EC%8B%A0%EC%9D%B4%20%EB%96%B4%EB%8B%A4" },
			{ "보이스트롯",
					"https://search.naver.com/search.naver?where=nexearch&query=%EB%B3%B4%EC%9D%B4%EC%8A%A4%ED%8A%B8%EB%A1%AF&ie=utf8&sm=tab_she&qdt=0" } };

	@Autowired
	TimeService service;

	@Scheduled(cron = "50 05 22 * * ?")
//	@Scheduled(cron = "0 0 0 * * ?")
	public void insertTodaySchedule() throws Exception {
		service.deleteYesterDaySchedule();

		ArrayList<BroadCastingDto> list = new ArrayList<>();
		try {
			for (int i = 0; i < broad4.length; i++) {
				System.out.println(broad4[i][0]);
				Document doc = Jsoup.connect(broad4[i][1]).get();
				ArrayList<String> brlist = new ArrayList<>();
				Elements elb = doc.select("td.first");
				for (Element bel : elb) {
					brlist.add(bel.select("a").text().replaceAll(" ", ""));
				}
				Elements el = doc.select("td.today");
				int cnt = 0;
				for (Element e : el) {
					Elements el2 = e.select("span.time_min");
					for (Element ee : el2) {
						BroadCastingDto dto = new BroadCastingDto();
						dto.setBc_company(brlist.get(cnt));
						dto.setBc_time(ee.text());
						dto.setBc_title(broad4[i][0]);
						list.add(dto);
					}
					cnt++;
				}
			}
			service.insertTodaySchedule(list);
		} catch (IOException e1) {
			System.out.println("크롤링 실패");
			e1.printStackTrace();
		}
	}


	// 하루에 한번 스케쥴 넣기
//	@Scheduled(cron = "0 0 0 * * ?")
	@Scheduled(cron = "50 05 22 * * ?")
	public void insertSingerSchedule() throws Exception {
		// db 가수 리스트 받아옴.
		List<SingerDto> dsList = service.selectSinger();
		for (int i = 0; i < dsList.size(); i++) {
			if(dsList.get(i).getS_cafeUrl()==null) {
				dsList.remove(i);
			}
		}
		// 스케줄 삭제
		service.deleteSingerSchedule();

		// 크롤링시작.
		System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
		ChromeOptions optins = new ChromeOptions();
		optins.addArguments("headless");
		WebDriver driver = new ChromeDriver(optins); // Driver 생성
		List<BroadCastingDto> slist = new ArrayList<>();
		for (int i = 0; i < dsList.size(); i++) {
			System.out.println(dsList.get(i).getS_name());
			driver.get(dsList.get(i).getS_cafeUrl()); // URL로 접속하기

			// System.out.println(driver.getPageSource());
			List<WebElement> schedule_detail_list = driver.findElements(By.className("schedule_detail"));
			for (WebElement web : schedule_detail_list) {
				String txt_day = web.findElement(By.className("txt_day")).getText();
				int a = web.findElements(By.className("inner_tit")).size();
				for (int idx = 0; idx < a; idx++) {
					BroadCastingDto time = new BroadCastingDto();
					time.setBc_date(txt_day);
					String inner_tit = web.findElement(By.className("inner_tit")).getText();
					time.setBc_time(inner_tit);
					String tit_subject = web.findElement(By.className("tit_subject")).getText();
					time.setBc_title(tit_subject);
					time.setBc_member(dsList.get(i).getS_name());
					slist.add(time);
				}
			}
		}
		driver.quit();

		// 가수 리스트 디비 저장
		service.insertSingerSchedule(slist);
	}

	
}
