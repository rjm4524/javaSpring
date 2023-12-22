package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.MemBoardDto;

public interface MemberService {

	// 멤버,게시판정보 가져오기
	ArrayList<MemBoardDto> selectAll3();
}
