package com.java.www.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;
import com.java.www.service.CService;

@Controller
@RequestMapping("customer")
public class CustomerController {
@Autowired
BService bService;
	
	@GetMapping("notice")
	public String notice(Model model) {
		List<BoardDto> list = bService.selectAll();
		
		model.addAttribute("list",list);
		return "customer/notice";
	}
	@GetMapping("notice_write")
	public String notice_write() {
		return "customer/notice_write";
	}
	@GetMapping("notice_view")
	public String notice_view(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		
		model.addAttribute("map",map);
		return "customer/notice_view";
	}
}
