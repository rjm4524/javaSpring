package com.java.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {
@Autowired
BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll(); 
		
		return list;
	}

	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevdto = boardMapper.selectPrev(bno);
		BoardDto nextdto = boardMapper.selectNext(bno);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevdto);
		map.put("nextdto", nextdto);
		return map;
	}

	@Override
	public void write(BoardDto bdto) {
		boardMapper.write(bdto);
		
	}

}
