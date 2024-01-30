package com.java.service;

import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;

@Service
public interface MService {
	//로그인
	int login(MemberDto mdto);

}
