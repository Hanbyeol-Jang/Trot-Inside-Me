package com.web.curation.dao;

import java.util.List;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.BoardPK;
import com.web.curation.dto.BroadCastingDto;
import com.web.curation.dto.GoodDto;
import com.web.curation.dto.ReplyDto;
import com.web.curation.dto.SingerDto;

public interface BoardDao {
	
	List<SingerDto> singerAllList();
	SingerDto singerSearch(int s_idx);
	//메인리스트 
	List<BoardDto> mainlist(int b_type);
	
	public void insertVideo(BoardDto boardDto); // 네이버 동영상 DB에 넣기
	public void insertArticle(BoardDto boardDto); // 네이버 기사 DB에 넣기
	public List<BoardDto> selectVideoList(String s_name); // s_name에 대한 전체 동영상 리스트 출력
	public List<BoardDto> selectArticleList(String s_name); // s_name에 대한 전체 기사 리스트 출력
	public boolean isUrl(String vno,String name); // 네이버 동영상 vno가 db에 있는지 확인
	boolean isUrl2(String url, String name); // 네이버 기사 url이 db에 있는지 확인
	
	//기사+동영상 디테일 
	public BoardPK detail(GoodDto dto);
	
	//댓글 리스트 
	List<ReplyDto> replylist(GoodDto b_idx);
	/* 댓글 작성 */
	int addComment(ReplyDto replyDto);

	/* 댓글 삭제 */
	int deleteComment(GoodDto replyDto);
	/* 좋아요 클릭 */
	int goodClick(GoodDto dto);
	/* 좋아요 취소 */
	int goodClickCancel(GoodDto dto);
	

	List<BroadCastingDto> broadCastAllList(); //편성표 전체 출력
	List<BroadCastingDto> singerScheduleList(String s_name);//가수 스케줄 리스트 출력
	
//	/* 좋아요 취소 */
//	int goodClickCancel(BoardPK boardPK);
//
//	/* 좋아요 수 표시 */
//	int goodCount(BoardPK boardPK);

	/* 댓글 수 표시 */
//	int commentCount(BoardPK boardPK);

}
