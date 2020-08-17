package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.AdminDto;
import com.web.curation.dto.SingerDto;

public interface AdminServcie {

	// 관리자 편성표 추가
	boolean addBroadSchedule(AdminDto dto);

	// 편성표 삭제
	boolean deleteBroadSchedule(int a_idx);

	// 편성표 리스트
	List<AdminDto> getBroadScheduleList();

	// 가수 추가
	boolean addSinger(SingerDto dto);

	// 가수 삭제
	boolean deleteSinger(int s_idx);

	// 가수 리스트
	List<SingerDto> getSingerList();

	// 편성표 이미지 출력
	String getTVImgUrl(int a_idx);

	// 가수 이미지 출력
	String getSingerImgUrl(int s_idx);

}
