package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;

public interface TimeService {

	// 지난 편성표 삭제
	void deleteYesterDaySchedule();

	// 편성표 추가
	void insertTodaySchedule(ArrayList<BroadCastingDto> list);

	// 가수 리스트
	List<SingerDto> selectSinger();

	// 지난 가수 스케줄 삭제
	void deleteSingerSchedule();

	// 가수 스케줄 추가
	void insertSingerSchedule(List<BroadCastingDto> slist);

	// 편성표 - 알림 서비스
	BroadCastingDto selectBroadCasting(int bc_idx);

	// 이미지 출력
	String GetImgByIdx(int a_idx);

}
