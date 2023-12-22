package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemBoardDto;
import com.java.www.mapper.Membermapper;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	Membermapper memmapper;
	
	//멤버,게시판 정보 가져오기
		@Override
		public ArrayList<MemBoardDto> selectAll3() {
			ArrayList<MemBoardDto> list = memmapper.selectAll3();

			return list;
		}
}
