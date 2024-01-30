package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.AlramDto;

import jakarta.servlet.http.HttpSession;



@Controller
public class FController {
	@Autowired
	HttpSession session;
	
	@RequestMapping("/")	
	public String index(){
		session.setAttribute("session_id","aaa");
		System.out.println("sessionId :"+session.getAttribute("session_id"));
		return "index";
	}

	
	@GetMapping("viewContent")
	public String viewContent() {
		
		return "view";
	}

}
