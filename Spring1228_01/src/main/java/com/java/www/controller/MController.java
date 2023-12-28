package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;
@Controller
@RequestMapping("member")
public class MController {
	
@Autowired
MService mService;
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("login")
	@ResponseBody
	public String login(MemberDto mdto) {
		System.out.println("MController id" +mdto.getId());
		System.out.println("MController id" +mdto.getPw());
		
		int result = mService.login(mdto);
		System.out.println("Mcontroller result"+result);
		return result+"";
	}
	
	@GetMapping("step03")
	public String step03() {
		
		return "member/step03";
	}
	@PostMapping("step03")
	public String step03(MemberDto mdto,String phone,String phone1,String phone2) {
		String phonenum = phone+"-"+phone1+"-"+phone2;
		mdto.setPhone(phonenum);
		int result = mService.Join(mdto);
		System.out.println("MController"+result);
		
		return "member/step03";
	}

}
