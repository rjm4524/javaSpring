package com.java.www.service;

import com.java.www.dto.MemberDto;
import com.java.www.dto.MemberDto2;

public interface MService {

	//로그인 확인
	int login(MemberDto2 mdto2);
	//아이디 이메일 검색
	String pwsearch(String id, String email);
	//아이디 찾기
	MemberDto2 idsearch(String name, String email);
	//아이디 중복확인
	String idcheck(String id);

}
