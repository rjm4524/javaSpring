package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.dto.MemberDto2;
import com.java.www.service.EmailService;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("member")
public class MController {

	@Autowired MService mService;
	@Autowired HttpSession session;
	@Autowired EmailService emailService;
	
	@GetMapping("id")
	public String id() {
		return "member/id";
	}
	@GetMapping("idsearch")
	public String idsearch() {
		return "member/idsearch";
	}
	
	@PostMapping("idsearch")
	@ResponseBody
	public String idsearch(String name,String email) {
		
		System.out.println("MController idsearch name"+name);
		System.out.println("MController idsearch email"+email);
		//service 연결 비밀번호 찾기 - 아이디 이메일 검색
		MemberDto2 mdto2 = mService.idsearch(name,email);
		String result ="";
		String tempId="";//임시아이디
		//찾은아이디에 뒤 두자리에 ** 변경
		if(mdto2!=null) { // abcde ->abc**
			tempId = mdto2.getId().substring(0,mdto2.getId().length()-2);
			tempId += "**";
			System.out.println("찾은 아이디 :"+tempId);
			result = tempId;
		}else {
			result="fail";
		}
		
		//System.out.println("MController idsearch result :"+result);
		
		
		return result;
	}
	@PostMapping("pwsearch")
	@ResponseBody
	public String pwsearch(String id,String email) {
		
		System.out.println(id);
		System.out.println(email);
		//service 연결 비밀번호 찾기 - 아이디 이메일 검색
		String result = mService.pwsearch(id,email);
		System.out.println("MController result :"+result);
		
		
		return result;
	}
	
	@PostMapping("idcheck")
	@ResponseBody
	public String idcheck(String id) {
		
		String result = mService.idcheck(id);
		
		return result;
	}
	
	
	
	@GetMapping("step01")
	public String step01() {
		return "member/step01";
	}
	
	@GetMapping("step02")
	public String step02() {
		return "member/step02";
	}
	@GetMapping("step03")
	public String step03() {
		return "member/step03";
	}
	@PostMapping("step04")
	public String step04(MemberDto2 mdto2) {
		System.out.println("MController phone"+mdto2.getPhone());
		return "member/step04";
	}
	
	@PostMapping("email")
	@ResponseBody
	public String email(String email) {
		System.out.println("MController email : "+email);
		
		//service연결 - 이메일주소 보냄.
		String result = emailService.mailSend(email);
		
		
		
		return result;
	}
	
	
	//---- login부분 ----
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	
	//------ 로그인 확인 부분 ----------
	//------ ajax 형태 ----------
	@PostMapping("ajaxLogin")
	@ResponseBody //데이터 전송
	public String ajaxLogin(MemberDto2 mdto2) {
		System.out.println("MController login id : "+mdto2.getId());
		System.out.println("MController login pw : "+mdto2.getPw());
		
		//service연결 - 1:성공,0:실패
		int result = mService.login(mdto2);
		System.out.println("MController login result : "+result);
		
		return result+"";
	}
	
	//------ jsp 형태 ----------
	@PostMapping("login")
	public String login(MemberDto2 mdto2,Model model) {
		System.out.println("MController login id : "+mdto2.getId());
		System.out.println("MController login pw : "+mdto2.getPw());
		
		//service연결 - 1:성공,0:실패
		int result = mService.login(mdto2);
		//model전송
		model.addAttribute("result",result);
		System.out.println("MController login result : "+result);
		return "member/doLogin";
	}
}
