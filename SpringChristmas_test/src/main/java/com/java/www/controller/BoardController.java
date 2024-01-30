package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
public class BoardController {
@Autowired
BService bService;
		
	//게시글 전체 가져오기
	@GetMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bService.selectAll();
		
		model.addAttribute("list", list);
		return "bList";
	}
	
	@GetMapping("bView")
	public String bView(@RequestParam(defaultValue = "1") int bno,Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		
		model.addAttribute("map",map);
		return "bView";
	}
	@GetMapping("bInsert") 
	public String bInsert() {
		return "bInsert";
	}
	@PostMapping("bInsert") 
	public String bInsert(BoardDto bdto, @RequestPart MultipartFile files,Model model) throws IllegalStateException, IOException {
		//MultipartFile - input type="file" name="files" name을 가져옴.
		if(!files.isEmpty()) {
			String orgName= files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName; //이름중복 방지
			String upload = "c:/upload/"; //업로드 위치
			File f = new File(upload+newName);
			files.transferTo(f);
			bdto.setBfile(newName);
		}else {
			bdto.setBfile("");
			
		}
		
		bService.Insert(bdto);
		model.addAttribute("bdto",bdto);
		return "doBInsert";
	}
	@GetMapping("bUpdate")
	public String bUpdate(@RequestParam(defaultValue = "1") int bno,Model model)  {
		Map<String, Object> map = bService.selectOne(bno);
		
		model.addAttribute("map",map);
		
		return "bUpdate";
	}
	@PostMapping("bUpdate")
	public String bUpdate(BoardDto bdto,@RequestPart MultipartFile files,Model model) throws IllegalStateException, IOException {
		if(!files.isEmpty()) {
			String orgName= files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String newName = time+"_"+orgName; //이름중복 방지
			String upload = "c:/upload/"; //업로드 위치
			File f = new File(upload+newName);
			files.transferTo(f);
			bdto.setBfile(newName);
		}else {
			bdto.setBfile("");
			
		}
		
		bService.bUpdate(bdto);
		
		
		
		return "bUpdate";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-----------------------------
	//---------다중업로드-------------
	//-----------------------------
	@GetMapping("bList2")
	public String bList2(Model model) {
		ArrayList<BoardDto> list = bService.selectAll();
		
		model.addAttribute("list",list);
		return"bList2";
	}
	
	@GetMapping("bView2")
	public String bView2(@RequestParam(defaultValue = "1") int bno,Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		
		model.addAttribute("map",map);
		return "bView2";
	}
	
	@GetMapping("bInsert2") 
	public String bInsert2() {
		return "bInsert2";
	}
	
	
	
	@PostMapping("bInsert2")
	public String bInsert2(BoardDto bdto,List<MultipartFile> files,Model model) throws IllegalStateException, IOException {
		//파일이 복수개일 경우 List<MultipartFile> files로 받음
		String orgName ="";
		String newName ="";
		String mergeName ="";
		int i=0;
		for(MultipartFile file:files) {
			orgName = file.getOriginalFilename();
			System.out.println("BController 파일첨부 이름 : "+orgName);
			long time = System.currentTimeMillis();
			newName = time+"_"+orgName; //중복방지 새 파일이름.
			String upload = "c:/upload/";
			File f = new File(upload+newName);
			file.transferTo(f);//파일을 upload에 저장시킴.
			
			//파일이름을 db에 저장하기
			if(i==0) mergeName += time+"_"+orgName;
			else mergeName += ","+time+"_"+orgName;
			i++;
		}
		
		bdto.setBfile(mergeName);
		System.out.println("BController 최종 파일첨부 이름 : "+mergeName);
		//db연결
		bService.Insert(bdto);
		
		return "bInsert2";
	}
	
	
	
}
