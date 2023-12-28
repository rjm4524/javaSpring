package com.java.www.service;

import com.java.www.dto.MemberDto;

public interface MService {
	
	//로그인 
	int login(MemberDto mdto);
	//회원가입
	int Join(MemberDto mdto);

}
