package com.java.www.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;

@Controller
public class DataController {
	
	
	@ResponseBody
	@RequestMapping("idCheck")
	public Map<String, Object> idCheck(String id) {
		//db검색
		Map<String, Object> map = new HashMap<>();
		ArrayList<MemberDto> list = new ArrayList<>();
		
		if(id.equals("aaa")) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		
		list.add(new MemberDto("bbb","1111","유관순",
				"010-2222-2222","female","game,golf",
				new Timestamp(System.currentTimeMillis())));
		list.add(new MemberDto("ccc","2222","이순신",
				"010-2222-2222","female","game,golf",
				new Timestamp(System.currentTimeMillis())));
		
		map.put("name", "홍길동");
		map.put("phone", "010-1111-1111");
		map.put("bno", 1);
		map.put("list", list);
		map.put("mdto", new MemberDto("bbb","1111","유관순",
				"010-2222-2222","female","game,golf",
				new Timestamp(System.currentTimeMillis())));
		
		//Json포맷 자동 변환 - Map,list 
		return map;
	}
	
	@ResponseBody//데이터로 리턴
	@RequestMapping("aaa")
	public String aaa() {
		return "aaa";
	}
	
	@ResponseBody
	@RequestMapping("boardBno/{bno}/{bhit}")
	public String boardBno(@PathVariable int bno,@PathVariable int bhit) {
		
		String txt = "boardBno 글번호 :"+bno;
		txt += ", 조회수 bhit :"+bhit;
		
		return txt;
	}
}
