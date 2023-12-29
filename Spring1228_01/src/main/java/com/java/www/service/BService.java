package com.java.www.service;

import java.util.List;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {

	List<BoardDto> selectAll();

	Map<String, Object> selectOne(int bno);

	void write(BoardDto bdto);

}
