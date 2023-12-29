package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	// 게시글 전체 가져오기
	// 게시글 총 갯수
	ArrayList<BoardDto> selectAll(int startRow, int endRow, String category, String sWord);
	int selectCountAll(String category, String sWord);

	
	//검색된 게시글 가져오기
	// 게시글 검색
	ArrayList<BoardDto> selectSearch(int startRow, int endRow, String category, String sWord);
	int selectSearchCount(String category, String sWord);

	// 게시글 1개 가져오기 - 현재글
	BoardDto selectOne(int bno);

	// 게시글 1개 가져오기 - 이전글
	BoardDto selectOnePrev(int bno);

	// 게시글 1개 가져오기 - 다음글
	BoardDto selectOneNext(int bno);

	// 게시글 저장
	int bInsert(BoardDto bdto);

	// 게시글 삭제
	int bDelete(int bno);

	// 게시글 수정저장
	int doBUpdate(BoardDto bdto);

	// --답글달기 저장--
	// 다른 게시글 bstep 증가
	int doBReply(BoardDto bdto);

	void bstepUp(BoardDto bdto);

	// 조회수1증가
	void bhitUp(int bno);
	
	

}
