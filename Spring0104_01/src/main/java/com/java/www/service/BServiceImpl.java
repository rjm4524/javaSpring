package com.java.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;
@Service
public class BServiceImpl implements BSerivce {
@Autowired
BoardMapper boardMapper;
	//게시글 리스트 가져오기.
	@Override
	public List<BoardDto> blist() {
		List<BoardDto> list = boardMapper.blist();
		return list;
	}
	//게시글 저장,파일업로드
	@Override
	public void write(BoardDto bdto) {
		boardMapper.write(bdto);
	}
	//게시글 1개 가져오기
	@Override
	public BoardDto selectOne(int bno) {
	 	BoardDto bdto = boardMapper.selectOne(bno);
		return bdto;
	}

}
