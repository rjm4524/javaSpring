package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class MServiceImpl implements MService {
@Autowired MemberMapper memberMapper;
@Autowired HttpSession session;	
	@Override
	public int login(MemberDto mdto) {
		int result = 0;
		System.out.println("MSerivceImpl "+result);
		MemberDto memberDto = memberMapper.login(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
		}
		return result;
	}	
	
	
	@Override
	public int Join(MemberDto mdto) {
		
		memberMapper.Join(mdto);
		int result = 1;
		return result;
	}

}
