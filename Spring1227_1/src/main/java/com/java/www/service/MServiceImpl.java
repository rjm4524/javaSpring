package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.dto.MemberDto2;
import com.java.www.mapper.MemberMapper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import jakarta.servlet.http.HttpSession;

@Service
public class MServiceImpl implements MService {

	@Autowired MemberMapper memberMapper;
	@Autowired HttpSession session;
	@Autowired JavaMailSender mailSender; //메일발송객체
	
	@Override //로그인 확인
	public int login(MemberDto2 mdto2) {
		int result = 0;
		//mapper연결 - return : dto
		MemberDto2 memberDto2 = memberMapper.login(mdto2);
		if(memberDto2 != null) {
			session.setAttribute("session_id", memberDto2.getId());
			session.setAttribute("session_name", memberDto2.getName());
			result = 1; //로그인성공
		}
		
		return result;
	}

	@Override
	public String pwsearch(String id, String email) {
		MemberDto2 mdto2 = memberMapper.pwsearch(id,email);
		String result = "";
		if(mdto2!=null) {
			result = "success";
			System.out.println("이메일 발송 프로그램을 진행합니다.");
			String pwcode = getCrateKey();
			System.out.println("psearch pwcode :"+pwcode);
			//섹션 저장
			//session.setAttribute("pwsearch_pwcode", pwcode);
			//이메일
			MimeMessage message = mailSender.createMimeMessage(); //html태그 전송가능 객체
			try {
				message.setSubject("[안내] 비밀번호찾기 임시비밀번호 전송 안내","utf-8");
				String htmlData = getHtmlData(pwcode);
				message.setText(htmlData,"utf-8","html");
				message.setFrom(new InternetAddress("rjm4524@naver.com"));//보내는 사람
				message.addRecipient(RecipientType.TO, new InternetAddress(email));
				//메일발송
				mailSender.send(message);
				//패스워드 변경
				memberMapper.update_pw(id,pwcode);
				
				System.out.println("이메일 발송이 완료되었습니다.");
				
				
			} catch (Exception e) {e.printStackTrace();}
			
			
		}else {result = "fail";}
		System.out.println("MServiceImpl pwsearch result : "+result);
		return result;
	}
	//아이디 중복확인
		@Override
		public String idcheck(String id) {
			
			MemberDto2 mdto2 = memberMapper.idcheck(id);
			String result ="";
			
			if(mdto2!=null) {
				result = "사용불가";
			}else {
				result = "사용가능";
			}
			return result;
		}
	
	
	//임시비밀번호 생성
	public String getCrateKey() {
		char[] charSet = new char[] {
		'0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','i','j','k','l','n','m','o',
		'p','q','r','s','t','u','v','w','x','y','z'
	};
	
	String pwcode ="";
	int idx=0;
	for(int i=0;i<10;i++) {
		idx = (int)(Math.random()*36);
		pwcode += charSet[idx];
	}
	
	
		return pwcode;	
	}
	//html 데이터
	public String getHtmlData(String pwcode) {
		String data = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\r\n"
				+ "<html xmlns='http://www.w3.org/1999/xhtml' lang='ko' xml:lang='ko'>\r\n"
				+ "<head>\r\n"
				+ "<meta http-equiv='Content-Type' content='application/xhtml+xml; charset=utf-8' />\r\n"
				+ "<meta http-equiv='X-UA-Compatible' content='IE=edge' />\r\n"
				+ "<title> 비밀번호 발송 </title>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "</head>\r\n"
				+ "<body bgcolor='#FFFFFF' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0' style='margin:0; padding:0; font:normal 12px/1.5 돋움;'>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "<table width='700' cellpadding='0' cellspacing='0' align='center'>\r\n"
				+ "<tr>\r\n"
				+ "	<td style='width:700px;height:100px;padding-top:30px;margin:0;vertical-align:top;font-size:0;line-height:0;'>\r\n"
				+ "		<img style='display:block; margin:0 auto;' src='https://www.sktelecom.com/images/common/2021/header-logo_lg.png' alt='JARDIN' />					\r\n"
				+ "	</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr>\r\n"
				+ "	<td style='width:700px;height:78px;padding:0;margin:0;vertical-align:top;font-weight:900 '>\r\n"
				+ "		<p style='width:620px;margin:20px auto 0;text-align:center;font-size:30px;'>\r\n"
				+ "		원두커피의 名家, JARDIN </p>\r\n"
				+ "		<p style='width:620px;margin:0 auto 15px;text-align:center;font-size:30px;line-height:40px;'>\r\n"
				+ "		임시 비밀번호가 발급되었습니다.</p>\r\n"
				+ "	</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr>\r\n"
				+ "	<td style='width:700px;height:196px;padding:0;margin:0;vertical-align:top;'>\r\n"
				+ "		<table width='618' height='194' cellpadding='0' cellspacing='0' align='center' style='margin:0 0 0 40px;border:1px #d9d9d9 solid;'>\r\n"
				+ "		<tr>\r\n"
				+ "			<td style='width:618px;height:194px;box-sizing:border-box; padding-top:30px;margin:0;vertical-align:top;font-size:0;line-height:0;background:#f9f9f9;'>\r\n"
				+ "				<p style='width:620px;margin:10px 0 0 0;padding:0;text-align:center;color:#888888;font-size:12px;line-height:1;'>아래의 PASSWORD는 임시 PASSWORD이므로 홈페이지 로그인 후 다시 수정해 주십시오.</p>\r\n"
				+ "				<p style='width:620px;margin:28px 0 0 0;padding:0;text-align:center;color:#666666;font-size:12px;line-height:1;'><strong>임시 패스워드 : <span style='color:#f7703c;line-height:1;'>"+pwcode+"</span></strong></p>\r\n"
				+ "				<p style='width:620px;margin:30px 0 0 0;padding:0;text-align:center;color:#888888;font-size:12px;line-height:1.4;'>쟈뎅샵에서는 고객님께 보다 나은 서비스를 제공하기 위해 항상 노력하고 있습니다.<br/>앞으로 많은 관심 부탁드립니다.</p>\r\n"
				+ "			</td>\r\n"
				+ "		</tr>\r\n"
				+ "		</table>	\r\n"
				+ "	</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr>\r\n"
				+ "	<td style='width:700px;height:100px;padding:0;margin:0;vertical-align:top;'>\r\n"
				+ "		<p style='width:700px;margin:30px 0 50px 0;text-align:center; font-size:20px; font-weight: 700;'><a href='http://localhost:8000'>JARDIN 바로가기</a></p>\r\n"
				+ "	</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr>\r\n"
				+ "	<td style='width:700px;height:140px;padding:0;margin:0;vertical-align:top;background-color:#5a524c;'>\r\n"
				+ "		<p style='width:620px;margin:20px 0 0 40px;padding:0;text-align:center;line-height:1.5;color:#b2aeac;'>메일수신을 원치 않으시는 분은 로그인 후. <span style='color:#ffffff'>메일 수신 여부</span>를 변경해주시기 바랍니다.<br/>IF YOU DO NOT WISH TO RECEIVE EMAILS FROM US, PLEASE LOG-IN AND UPDATE<br/> YOUR MEMBERSHIP INFORMATION.</p>\r\n"
				+ "\r\n"
				+ "		<p style='width:620px;margin:15px 0 0 40px;padding:0;text-align:center;line-height:1.5;color:#b2aeac;'><span style='color:#ffffff'>본 메일에 관한 문의사항은 사이트 내 고객센터를 이용해주시기 바랍니다.</span><br/>COPYRIGHT ©  2014 JARDIN ALL RIGHTS RESERVED.</p>\r\n"
				+ "	</td>\r\n"
				+ "</tr>\r\n"
				+ "</table>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "</div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		return data;
	}

	@Override //아이디 찾기 - name,email
	public MemberDto2 idsearch(String name, String email) {
		MemberDto2 mdto2 = memberMapper.idsearch(name,email);
		return mdto2;
	}
	
	
}
