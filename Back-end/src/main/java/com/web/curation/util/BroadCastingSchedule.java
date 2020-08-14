package com.web.curation.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

import com.web.curation.dto.AdminDto;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.service.AdminServcie;
import com.web.curation.service.TimeService;

@Component
public class BroadCastingSchedule {

	@Autowired
	TimeService timeService;

	@Autowired
	AdminServcie adminService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void insertTodaySchedule() throws Exception {
		// 편성표 삭제
		timeService.deleteYesterDaySchedule();
		
		//편성표 url 리스트 가져옴. 
		List<AdminDto> broad = adminService.getBroadScheduleList();

		//크롤링 시작
		ArrayList<BroadCastingDto> list = new ArrayList<>();
		try {
			for (int i = 0; i < broad.size(); i++) {
				System.out.println(broad.get(i).getA_broadName());
				Document doc = Jsoup.connect(broad.get(i).getA_broadUrl()).get();
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
						dto.setBc_title(broad.get(i).getA_broadName());
						dto.setA_idx(broad.get(i).getA_idx());
						System.out.println(broad.get(i).getA_idx());
						list.add(dto);
					}
					cnt++;
					
					for (BroadCastingDto element : list) {
						System.out.println(element);
					}
				}
			}
			timeService.insertTodaySchedule(list);
		} catch (IOException e1) {
			System.out.println("크롤링 실패");
			e1.printStackTrace();
		}
	}


	// 하루에 한번 스케쥴 넣기
//	@Scheduled(cron = "0 0 0 * * ?")
	@Scheduled(cron = "0 30 10 * * ?")
	public void insertSingerSchedule() throws Exception {
		// db 가수 리스트 받아옴.
		List<SingerDto> dsList = timeService.selectSinger();
		for (int i = 0; i < dsList.size(); i++) {
			if(dsList.get(i).getS_cafeUrl()==null) {
				dsList.remove(i);
			}
		}
		// 스케줄 삭제
		timeService.deleteSingerSchedule();

		// 크롤링시작.
		System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
		ChromeOptions optins = new ChromeOptions();
		optins.addArguments("headless");
		WebDriver driver = new ChromeDriver(optins); // Driver 생성
		List<BroadCastingDto> slist = new ArrayList<>();
		for (int i = 0; i < dsList.size(); i++) {
			System.out.println(dsList.get(i).getS_name());
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.get(dsList.get(i).getS_cafeUrl());
			
//			 System.out.println(driver.getPageSource());
			List<WebElement> schedule_detail_list = driver.findElements(By.className("schedule_detail"));
			for (WebElement web : schedule_detail_list) {
				String txt_day = web.findElement(By.className("txt_day")).getText();
				int a = web.findElements(By.className("inner_tit")).size();
				if(a==1) {
					BroadCastingDto time = new BroadCastingDto();
					time.setBc_date(txt_day);
					String inner_tit = web.findElement(By.className("inner_tit")).getText();
					time.setBc_time(inner_tit);
					String tit_subject = web.findElement(By.className("tit_subject")).getText();
					time.setBc_title(tit_subject);
					time.setBc_member(dsList.get(i).getS_name());
					slist.add(time);
				}else {
					List<WebElement> timel = web.findElements(By.className("inner_tit"));
					List<WebElement> titlel = web.findElements(By.className("tit_subject"));
					for (int j = 0; j < a; j++) {
						BroadCastingDto time = new BroadCastingDto();
						time.setBc_date(txt_day);
						time.setBc_time(timel.get(j).getText());
						time.setBc_title(titlel.get(j).getText());
						time.setBc_member(dsList.get(i).getS_name());
						slist.add(time);
					}
				
				}
			}
		}
		driver.quit();

		// 가수 리스트 디비 저장
		timeService.insertSingerSchedule(slist);
	}

	
}
