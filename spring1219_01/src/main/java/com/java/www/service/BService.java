package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {

	// 게시글 전체 가져오기
	Map<String, Object> selectAll(int page, String category, String sWord);

	// 게시글 검색
	Map<String, Object> selectSearch(int page, String category, String sWord);

	// 게시글 1개 가져오기
	Map<String, Object> selectOne(int bno);

	// 게시글 저장
	void bInsert(BoardDto bdto);

	// 게시글 삭제
	void bDelete(int bno);

	// 게시글 수정저장
	void doBUpdate(BoardDto bdto);

	// 답변달기 저장
	void doBReply(BoardDto bdto);

}
