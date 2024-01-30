package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {
	
	//게시글 전체 가져오기
	ArrayList<BoardDto> selectAll();
	
	//게시글 보기,이전글 다음글
	Map<String, Object> selectOne(int bno);
	
	//게시글 등록
	void Insert(BoardDto bdto);
	
	//게시글 수정
	void bUpdate(BoardDto bdto);

}
