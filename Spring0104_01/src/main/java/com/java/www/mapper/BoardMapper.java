package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {
	//게시글 리스트 가져오기
	List<BoardDto> blist();
	//게시글 저장 및 파일 업로드
	void write(BoardDto bdto);
	//게시글 1개 가져오기
	BoardDto selectOne(int bno);

}
