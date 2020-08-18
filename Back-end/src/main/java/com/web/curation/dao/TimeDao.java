package com.web.curation.dao;

import java.util.ArrayList;
import java.util.List;

import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.SingerDto;

public interface TimeDao {

	// 지난 편성표 삭제
	void deleteYesterDaySchedule();

	// 현재 편성표 추가
	void insertTodaySchedule(ArrayList<BroadCastingDto> list);

	// 가수 리스트 출력
	List<SingerDto> selectSinger();

	// 지난 가수 스케줄 삭제
	void deleteSingerSchedule();

	// 가수 스케줄 추가
	void insertSingerSchedule(List<BroadCastingDto> slist);

	// 편성표 - 알림서비스
	BroadCastingDto selectBoradCasting(int bc_idx);

	// 편성표 이미지 출력
	String getImgByIdx(int a_idx);

}
