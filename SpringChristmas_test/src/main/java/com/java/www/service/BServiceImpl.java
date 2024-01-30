package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {
@Autowired
BoardMapper BMapper;
	
	
	//게시글 전체 가져오기
	@Override
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = BMapper.selectAll();
		
		return list;
	}

	//게시글 보기 이전글 다음글
	@Override
	public Map<String, Object> selectOne(int bno) {
		
		BoardDto bdto = BMapper.selectOne(bno);
		BoardDto prevdto = BMapper.selectPrev(bno);
		BoardDto nextdto = BMapper.selectNext(bno);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevdto);
		map.put("nextdto", nextdto);
		
		return map;
	}
	
	//게시글 작성
	@Override
	public void Insert(BoardDto bdto) {
		BMapper.Insert(bdto);
		
	}
	
	//게시글 수정
	@Override
	public void bUpdate(BoardDto bdto) {
		BMapper.bUpdate(bdto);
		
	}
	

	

}
