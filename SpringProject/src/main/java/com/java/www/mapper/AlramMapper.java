package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.AlramDto;

@Mapper
public interface AlramMapper {
	//알람 전체 가져오기
	List<AlramDto> selectAlram(String user_id);
	//확인한 알람 목록 가져오기
	List<AlramDto> selectChecked(String user_id);
	//알람삭제
	void delectAlram(int alram_id);

}
