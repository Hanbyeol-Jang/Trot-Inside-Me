package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.ReplyDto;
import com.web.curation.dto.SingerDto;

public interface BoardService {

	//가수 전체 리스트 
	List<SingerDto> singerAllList();
	//가수 디테일 
	SingerDto singerSearch(int s_idx);
	//메인 리스트 (좋아요 순)
	List<BoardDto> mainlist(int b_type, int page);
		
	public void insertVideo(String s_name); // 네이버 동영상 DB에 넣기
	public void insertArticle(String s_name); // 네이버 기사 DB에 넣기
	public List<BoardDto> selectVideoList(String s_name); // s_name에 대한 전체 동영상 리스트 출력
	public List<BoardDto> selectArticleList(String s_name); // s_name에 대한 전체 기사 리스트 출력
	public boolean isUrl(String vno, String name); // 네이버 동영상 vno가 db에 있는지 확인
	public boolean isUrl2(String url, String name); // 네이버 기사 url이 db에 있는지 확인
	//동영상 디테일 
	public BoardDto videoDetail(int b_idx);
	//기사 디테일
	public BoardDto articleDetail(int b_idx);
	//댓글리스트 
	List<BoardDto> replylist(int b_idx);
		
	/* 댓글 작성 */
	boolean addComment(ReplyDto replyDto);
	
	/* 댓글 삭제 */
	boolean deleteComment(ReplyDto replyDto);
	
	/* 좋아요 클릭 */
	boolean goodClick(GoodDto dto);
	/* 좋아요 취소 */
	boolean goodClickCancel(GoodDto dto);
	
	List<BroadCastingDto> broadCastAllList(); //편성표 전체 출력
	List<BroadCastingDto> singerScheduleList(String s_name); //가수 스케줄 출력
	
	
	
	

//
//	/* 좋아요 수 표시 */
//	int goodCount(BoardPK boardPK);


	/* 댓글 수 표시 */
//	int commentCount(BoardPK boardPK);
}
