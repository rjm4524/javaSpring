package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.mapper.Empmapper;
import com.java.www.mapper.Membermapper;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	Empmapper empmapper;
	

	// 사원정보가져오기
	@Override
	public ArrayList<EmpDto> selectAll() {
		ArrayList<EmpDto> list = empmapper.selectAll();

		return list;
	}

	// 사원,부서정보 가져오기
	@Override
	public ArrayList<EmpDeptDto> selectAll2() {
		ArrayList<EmpDeptDto> list = empmapper.selectAll2();

		return list;
	}
	
	
	

}
