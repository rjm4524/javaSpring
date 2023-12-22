package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemBoardDto;
@Mapper
public interface Membermapper {
	//멤버,게시판 정보 가져오기.
		ArrayList<MemBoardDto> selectAll3();
}
