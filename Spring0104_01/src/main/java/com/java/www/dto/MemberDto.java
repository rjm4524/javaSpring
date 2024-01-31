package com.java.www.dto;

import java.sql.Timestamp;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
	
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String gender;
	private String hobby;
	private Timestamp mdate;
}