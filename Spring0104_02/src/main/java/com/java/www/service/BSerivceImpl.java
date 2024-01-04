package com.java.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BSerivceImpl implements BService{
@Autowired BoardMapper boardmapper;
	@Override
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardmapper.selectAll();
		return list;
	}
	//게시글 업로드
	@Override
	public void write(BoardDto bdto) {
		boardmapper.write(bdto);
		
	}
	@Override
	public BoardDto selectOne(int bno) {
		BoardDto bdto = boardmapper.selectOne(bno);
		return bdto;
	}
	@Override
	public BoardDto bupdate(BoardDto bdto) {
		BoardDto bdto2 = boardmapper.bupdate(bdto);
		return bdto2;
		
	}

}
