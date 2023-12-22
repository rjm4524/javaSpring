package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.service.EmpService;
import com.java.www.service.MemberService;
@Controller
public class MController {
	@Autowired
	MemberService mService;
	
	
	
	
	@GetMapping("list3")
	public String list3(Model model) {
		ArrayList<MemBoardDto> list = mService.selectAll3();
		
		model.addAttribute("list",list);
		return "list3";
	}
}
