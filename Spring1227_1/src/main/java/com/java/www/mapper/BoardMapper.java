package com.java.www.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//공지사항 전체가져오기
	ArrayList<BoardDto> selectAll(int startRow, int endRow, String category,String sWord);
	//게시글 검색
	int selectCountAll(String category, String sWord);

	//게시글 1개 가져오기
	BoardDto selectOne(int bno);

	// 하단댓글 모두 가져오기
	List<BCommentDto> bCommentSelectAll(int bno);

	//댓글1개 저장
	void bCommentInsert(BCommentDto cdto);

	//댓글1개 가져오기
	BCommentDto bCommentSelectOne(int cno);

	//댓글 삭제
	int bCommentDelete(int cno);

	//댓글 수정저장
	void BCommentUpdate(BCommentDto cdto);
	
	//검색된 게시글 갯수
	int selectSearchCountAll(String category, String sWord);
	//검색된 게시글 보기
	ArrayList<BoardDto> selectSearch(int startRow, int endRow, String category, String sWord);



}
