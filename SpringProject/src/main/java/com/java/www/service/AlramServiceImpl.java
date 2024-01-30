package com.java.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.AlramDto;
import com.java.www.mapper.AlramMapper;

@Service
public class AlramServiceImpl implements AlramService {
	@Autowired
	AlramMapper alramMapper;
	
	//알람 전체 가져오기
	@Override
	public List<AlramDto> selectAlram(String user_id) {
		System.out.println(""+user_id);
		List<AlramDto> list = alramMapper.selectAlram(user_id);
		return list;
	}
	//확인한 알람 가져오기
	@Override
	public List<AlramDto> selectChecked(String user_id) {
		List<AlramDto> list = alramMapper.selectChecked(user_id);
		return list;
	}
	//알람 삭제
	@Override
	public void delectAlram(int alram_id) {
		alramMapper.delectAlram(alram_id);
		
	}

}
