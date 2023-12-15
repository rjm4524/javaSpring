package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
@RequestMapping("member")
public class MController {
	
	@RequestMapping("mInsert")
	public String mInsert() {
		return "member/mInsert";
	}
	@RequestMapping("memberUpdate")
	public String memberUpdate(MemberDto mdto,Model model) {
		
		model.addAttribute("mdto", mdto);
		return "member/memberUpdate";
	}
	@RequestMapping("memberView")
	public String memberView(MemberDto mdto,Model model) {
		
		model.addAttribute("mdto", mdto);
		return "member/memberView";
	}
	@RequestMapping("doMInsert")
	public String doMInsert(MemberDto mdto,Model model) {
		System.out.println("MController hobby :"+mdto.getHobby());
		model.addAttribute("mdto", mdto);
		return "member/memberView";
	}
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	@RequestMapping("doLogin")
	public String doLogin(MemberDto memberDto,BoardDto bdto,Model model) {
		System.out.println("controller id :"+memberDto.getId());
		System.out.println("controller pw :"+memberDto.getPw());
		System.out.println("controller bno :"+bdto.getBno());
		String id = memberDto.getId();
		String pw = memberDto.getPw();
		int bno = bdto.getBno();
		//기본생성자
		MemberDto mdto = new MemberDto();
		mdto.setId(memberDto.getId());
		System.out.println("mdto.getId :"+mdto.getId());
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		//전체생성자
		MemberDto mdto2 = new MemberDto(id,pw, "홍길동", "010-1111-1111", "male", "game,golf",mdate); 
		//부분생성자
		MemberDto mdto3 = MemberDto.builder().id(id).pw(pw).name("박박박").gender("female").build();
		System.out.println("controller mdto3 name :"+ mdto3.getName());
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("bno", bno);
		model.addAttribute("mdto", memberDto);
		
		return "member/doLogin";
	}
}
