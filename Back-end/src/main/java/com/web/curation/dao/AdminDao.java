package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.AdminDto;
import com.web.curation.dto.SingerDto;
import com.web.curation.dto.UserDto;

public interface AdminDao {

	// 관리자 편성표 추가
	int addBroadSchedule(AdminDto dto);

	// 편성표 삭제
	int deleteBroadSchedule(int a_idx);

	// 편성표 리스트
	List<AdminDto> getBroadScheduleList();

	// 가수 추가
	int addSinger(SingerDto dto);

	// 가수 삭제
	int deleteSinger(int s_idx);

	// 가수 리스트
	List<SingerDto> getSingerList();

	// 편성표 이미지 출력
	String getTVImgUrl(int a_idx);

	// 가수 이미지 출력
	String getSingerImgUrl(int s_idx);

	// 관리자 로그인
	UserDto adminLogin(UserDto userDto);

}
