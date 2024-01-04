package com.java.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;

@Service
public interface BService {

	List<BoardDto> selectAll();
	
	void write(BoardDto bdto);

	BoardDto selectOne(int bno);

	BoardDto bupdate(BoardDto bdto);

}
