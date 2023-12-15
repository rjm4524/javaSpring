package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
public class FController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("mlogin")
	public String doLogin(MemberDto mdto, Model model) {
		
		model.addAttribute("mdto",mdto);
		return "mlogin";
	}
	@RequestMapping("mInsert")
	public String mInsert() {
		return "mInsert";
	}
	//-----------board------------------
	@RequestMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}
	@RequestMapping("bUpdate")
	public String bUpdate(BoardDto bdto,Model model) {
		
		model.addAttribute("bdto",bdto);
		return "bUpdate";
	}
	@RequestMapping("bView")
	public String bView(BoardDto bdto, Model model) {
		
		model.addAttribute("bdto",bdto);
		return "bView";
	}
	
}
