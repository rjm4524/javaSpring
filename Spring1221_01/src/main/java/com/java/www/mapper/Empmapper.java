package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;

@Mapper
public interface Empmapper {
	//사원정보가져오기
	ArrayList<EmpDto> selectAll();
	
	//사원부서정보 가져오기
	ArrayList<EmpDeptDto> selectAll2();
	

}
