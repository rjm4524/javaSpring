package com.java.www.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.www.dto.KaKaoDto;
import com.java.www.dto.OAuthToken;

import jakarta.servlet.http.HttpSession;


@Controller
public class FContoller {
	@Autowired
	HttpSession session;
	
	@GetMapping("/")
	public String main() {
		
		return "main";
	}
	
	@GetMapping("login")
	public String login() {
		
		return "login";
	}
	
	//카카오 로그인 ---------------------------
	//코드 받기
	@Controller
	public class KakaoController {
		@Autowired
		HttpSession session;
		// 카카오 로그인
		// http://localhost:8000/kakao/oauth
		@GetMapping("/kakao/oauth")
		//@ResponseBody
		public String kakaoLogin(String code) {
			// ①. code값 받기
			System.out.println("kakao code : " + code);
			// ②. token키 받기
			String tokenUrl = "https://kauth.kakao.com/oauth/token";
			// ▽headers에 담기
			String content_type = "application/x-www-form-urlencoded;charset=utf-8";
			// ▽body에 담기
			String grant_type = "authorization_code";
			String client_id = "d4b05d938959c8fa6bfe46bd9179b8b9";
			String redirect_uri = "http://localhost:8000/kakao/oauth";
			// code = code;
			// 공공데이터에서 사용 : HttpURLConnection conn = (HttpURLConnection)
			// url.openConnection();
			// ▼ url전송 객체선언
			RestTemplate rt = new RestTemplate();
			// ▼ HttpHeaders 오브젝트 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content_type", content_type);
			// ▼ body 오브젝트 생성
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", grant_type);
			params.add("client_id", client_id);
			params.add("redirect_uri", redirect_uri);
			params.add("code", code);
			// ▼ 합치는 오브젝트 생성 : HttpHeaders 와 MultiValueMap을 합쳐서 전송
			HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);
			// http 전송 : post방식, 응답은 response
			ResponseEntity<String> response = rt.exchange(tokenUrl, HttpMethod.POST, tokenRequest, String.class);
			// json데이터를 java오브젝트로 변환 : ObjectMapper
			ObjectMapper objectMapper = new ObjectMapper();
			OAuthToken oAuthToken = null;
			// json데이터를 java오브젝트로 변경
			try {
				oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("카카오 토큰키 : " + response);
			System.out.println("카카오 토큰키 getBody : " + response.getBody());
			System.out.println("카카오 엑세스 getAccess_token : " + oAuthToken.getAccess_token());
			System.out.println("카카오 엑세스 getExpires_in : " + oAuthToken.getExpires_in());
			// *** ③.사용자 정보 가져오기
			// ▼ HttpHeaders 오브젝트 생성
			HttpHeaders headers_user = new HttpHeaders();
			headers_user.add("Content_type", content_type);
			headers_user.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
			// ▼ 합치는 오브젝트 생성 : HttpHeaders 와 MultiValueMap을 합쳐서 전송
			HttpEntity<MultiValueMap<String, String>> tokenRequest_user = new HttpEntity<>(headers_user);
			String userUrl = "https://kapi.kakao.com/v2/user/me";
			// http 전송 : post방식, 응답은 response
			ResponseEntity<String> response_user = rt.exchange(userUrl, HttpMethod.POST, tokenRequest_user, String.class);
			System.out.println("사용자 정보 response_user : " + response_user);
			// ----------------
			// json데이터를 java오브젝트로 변환 : ObjectMapper
			ObjectMapper objectMapper2 = new ObjectMapper();
			KaKaoDto kaKaoDto = null;
			
			// json데이터를 java오브젝트로 변경
			try {
				kaKaoDto = objectMapper2.readValue(response_user.getBody(), KaKaoDto.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("카카오 사용자 정보  response_user: " + response_user);
			System.out.println("카카오 사용자 정보  kaKaoDto id: " + kaKaoDto.getId());
			System.out.println("카카오 사용자 정보  kaKaoDto connected_at: " + kaKaoDto.getConnected_at());
			System.out.println("카카오 사용자 정보  kaKaoDto getNickname: " + kaKaoDto.getProperties().getNickname());
			
			//----로그인을 시켜줌 . member 테이블에 id : id, password : id_1234567890
			session.setAttribute("session_id", kaKaoDto.getId());
			session.setAttribute("session_name", kaKaoDto.getProperties().getNickname());
			
			
			
			
			return "redirect:/";
			//return "사용자 정보 response_user : " + response_user;
		}// kakaoLogin(code)
	}// KakaoController
	
	
	
}
