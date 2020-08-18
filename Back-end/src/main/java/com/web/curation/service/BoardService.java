package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.ReplyDto;
import com.web.curation.dto.SingerDto;

public interface BoardService {

	// 가수 전체 리스트
	List<SingerDto> singerAllList();

	// 가수 디테일
	SingerDto singerDetail(GoodDto dto);

	// 가수 검색
	SingerDto singerSearch(int dto);

	// 메인 리스트 (좋아요 순)
	List<BoardPK> mainlist(int b_type);

	// 네이버 동영상 DB에 넣기
	public void insertVideo(String s_name);

	// 네이버 기사 DB에 넣기
	public void insertArticle(String s_name);

	// 가수 검색 후 영상 리스트
	public List<BoardPK> selectVideoList(String s_name);

	// 가수 검색 후 기사 리스트
	public List<BoardPK> selectArticleList(String s_name);

	// 네이버 동영상 vno가 db에 있는지 확인
	public boolean isUrl(String vno, String name);

	// 네이버 기사 url이 db에 있는지 확인
	public boolean isUrl2(String url, String name);

	// 기사+동영상 디테일
	public BoardPK detail(GoodDto dto);

	// 댓글리스트
	List<ReplyDto> replylist(GoodDto dto);

	/* 댓글 작성 */
	boolean addComment(ReplyDto replyDto);

	/* 댓글 삭제 */
	boolean deleteComment(GoodDto rDto);

	/* 좋아요 클릭 */
	boolean goodClick(GoodDto dto);

	/* 좋아요 취소 */
	boolean goodClickCancel(GoodDto dto);

	// 편성표 전체 출력
	List<BroadCastingDto> broadCastAllList();

	// 가수 스케줄 출력
	List<BroadCastingDto> singerScheduleList(int s_idx);
	
	// 키워드로 영상검색
	List<BoardPK> searchVideo(String word);
	

}
