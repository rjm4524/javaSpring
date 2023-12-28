package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper

public interface BoardMapper {

	List<BoardDto> selectAll();
	
	//게시글 1개보기
	BoardDto selectOne(int bno);
	//이전글 보기
	BoardDto selectPrev(int bno);
	//다음글 보기
	BoardDto selectNext(int bno);


}
