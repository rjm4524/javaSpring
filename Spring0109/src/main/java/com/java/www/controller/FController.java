package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class FController {
	
	@RequestMapping("/")	
	public String index()
	{
		
		return "index";
	}

	@GetMapping("alram")
	public String alram() {
		
		return "alram";
	}
	@GetMapping("alramCheck")
	public String alramCheck() {
		
		return "alramCheck";
	}
	@GetMapping("Mention")
	public String Mention() {
		
		return "Mention";
	}
	@GetMapping("viewContent")
	public String viewContent() {
		
		return "view";
	}

}
