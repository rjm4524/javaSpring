package com.java.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.www.dto.AlramDto;

@Service
public interface AlramService {
	//알람 전체 가져오기
	List<AlramDto> selectAlram(String user_id);
	//확인한 알람 전체 가져오기
	List<AlramDto> selectChecked(String user_id);
	//알람 삭제
	void delectAlram(int alram_id);

}
