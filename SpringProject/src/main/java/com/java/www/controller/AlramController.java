package com.java.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.www.dto.AlramDto;
import com.java.www.service.AlramService;

import jakarta.servlet.http.HttpSession;
@Controller
public class AlramController {
@Autowired
AlramService alramService;
@Autowired
HttpSession session;
	
	//알람 전부 가져오기
	@GetMapping("alram")
	public String alram(String user_id,Model model) {
		user_id = (String)session.getAttribute("session_id");
		System.out.println("user_id :"+user_id);
		List<AlramDto> list = alramService.selectAlram(user_id);
		model.addAttribute("list",list);
		return "alram";
	}
	@GetMapping("alramCheck")
	public String alramCheck(String user_id,Model model) {
		user_id = (String)session.getAttribute("session_id");
		List<AlramDto> list = alramService.selectChecked(user_id);
		model.addAttribute("list",list);
		return "alramCheck";
	}
	//알람 삭제
	@PostMapping("alramDelect")
	public String alramDelect(int alram_id) {
		System.out.println("alram_id :"+alram_id);
		alramService.delectAlram(alram_id);
		
		return "alramDelect";
	}
	
	
	
	@GetMapping("Mention")
	public String Mention() {
		
		return "Mention";
	}
	
	
}
