package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
public class FController {
@Autowired BService bservice;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("blist")
	public String blist(Model model) {
		List<BoardDto> list = bservice.selectAll();
		model.addAttribute("list",list);
		return "blist";
	}
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	@GetMapping("bupdate")
	public String bupdate(@RequestParam(defaultValue = "1")int bno,Model model) {
		BoardDto bdto = bservice.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "bupdate";
	}
	@GetMapping("bview")
	public String bview(@RequestParam(defaultValue = "1") int bno,Model model) {
		BoardDto bdto = bservice.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "bview";
	}
	//내용부분 - 이미지 추가시 호출
		@PostMapping("summernoteUpload")
		@ResponseBody
		public String summernoteUpload(@RequestPart MultipartFile file) throws Exception{
			String urllink = "";
			
			if(!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();
				long time = System.currentTimeMillis();
				String upFName = time+"_"+originalFileName;
				String fupload = "c:/upload/";
				
				//파일서버에 업로드 부분
				File f = new File(fupload+upFName);
				file.transferTo(f);
				
				
				//파일저장위치
				urllink = "/upload/"+upFName;
				System.out.println("파일 저장위치 :"+urllink);
			}
			
			return urllink;
		}
	
	@PostMapping("write")
	public String bwrite(BoardDto bdto,Model model,@RequestPart MultipartFile file) throws Exception{
		if(!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String upFName = time+"_"+originalFileName;
			String fupload = "c:/upload/";
			
			//파일서버에 업로드 부분
			File f = new File(fupload+upFName);
			file.transferTo(f);
			
			//bdto bfile 추가
			 bdto.setBfile(upFName);
			
			//파일저장위치
			String urllink = "/upload/"+upFName;
		}else {
			bdto.setBfile("");
		}
		System.out.println("파일이름 :"+bdto.getBfile());
		//파일저장 db연결
		bservice.write(bdto);
		return "doBwrite";
	}
	@PostMapping("bupdate")
	public String bupdate(BoardDto bdto,Model model,@RequestPart MultipartFile file) throws Exception{
		if(!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String upFName = time+"_"+originalFileName;
			String fupload = "c:/upload/";
			
			//파일서버에 업로드 부분
			File f = new File(fupload+upFName);
			file.transferTo(f);
			
			//bdto bfile 추가
			bdto.setBfile(upFName);
			
			//파일저장위치
			String urllink = "/upload/"+upFName;
		}else {
			bdto.setBfile("");
		}
		System.out.println("파일이름 :"+bdto.getBfile());
		//파일저장 db연결
		BoardDto bdto2 = bservice.bupdate(bdto);
		model.addAttribute("bdto2",bdto2);
		return "doBupdate";
	}
}
