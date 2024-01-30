package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class MServiceImpl implements MService {
@Autowired
MemberMapper Membermapper;
@Autowired
HttpSession session;
	
	@Override
	public int login(MemberDto mdto) {
		int result = 0;
		MemberDto memberDto = Membermapper.login(mdto);
		if(memberDto!=null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
		}
		return result;
	}
	
}	
