package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class MemberController {
	@Autowired
	HttpSession session;	
	@Autowired
	MService mService;
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("logout")
	public String logout() {
		session.invalidate(); 
		return "logout";
	}
	
	@RequestMapping("dologin")
	public String dologin(MemberDto mdto, Model model,HttpServletRequest request) {
		MemberDto memberDto = mService.selectOne(mdto);
		int result = 0;
		if(memberDto!=null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
		}else {
			
		}
		
		model.addAttribute("result",result);
		return "dologin";
	}
	
	
}
