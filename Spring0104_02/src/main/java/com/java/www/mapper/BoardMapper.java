package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	List<BoardDto> selectAll();
	
	void write(BoardDto bdto);

	BoardDto selectOne(int bno);

	BoardDto bupdate(BoardDto bdto);

}
