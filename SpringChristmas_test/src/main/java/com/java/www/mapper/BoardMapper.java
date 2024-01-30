package com.java.www.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	//게시글 전체 가져오기
	ArrayList<BoardDto> selectAll();

	BoardDto selectOne(int bno);

	BoardDto selectPrev(int bno);

	BoardDto selectNext(int bno);
	
	void Insert(BoardDto bdto);

	void bUpdate(BoardDto bdto);



}
