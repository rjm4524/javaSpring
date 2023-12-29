package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;

	// 게시글 검색
	@Override
	public Map<String, Object> selectSearch(int page, String category, String sWord) {
		// 하단넘버링 필요한것
		// page,rowPerpage-1페이지당 게시글 갯수,countAll,startPage,endPage,maxPage
		// 하단넘버링 변수
		if(page<=0) page=1;
		int CountPerPage = 10;
		int bottomPerNum = 10; // 하단넘버링 갯수
		int countAll = boardMapper.selectSearchCount(category,sWord); // 게시글 검색 총 갯수
		
		int maxPage = (int) Math.ceil((double) countAll / CountPerPage);
		int startPage = ((page - 1) / 10) * 10 + 1;
		int endPage = (startPage + bottomPerNum) - 1;
		// 게시글 관련 변수
		int startRow = (page - 1) * CountPerPage + 1;
		int endRow = startRow + CountPerPage - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		ArrayList<BoardDto> list = boardMapper.selectSearch(startRow, endRow,category,sWord);
		// 데이터 전송 - list,maxPage,startPage,endPage,page
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("countAll", countAll);
		return map;
	}

	// 게시글 전체 가져오기
	@Override
	public Map<String, Object> selectAll(int page,String category,String sWord) {
		// 하단넘버링 필요한것
		// page,rowPerpage-1페이지당 게시글 갯수,countAll,startPage,endPage,maxPage
		// 하단넘버링 변수
		int CountPerPage = 10;
		int bottomPerNum = 10; // 하단넘버링 갯수
		int countAll = boardMapper.selectCountAll(category,sWord); // 게시글 총 갯수
		System.out.println("검색된 게시글 수 selectAll :"+countAll);
		int maxPage = (int) Math.ceil((double) countAll / CountPerPage);
		int startPage = ((page - 1) / 10) * 10 + 1;
		int endPage = (startPage + bottomPerNum) - 1;
		// 게시글 관련 변수
		int startRow = (page - 1) * CountPerPage + 1;
		int endRow = startRow + CountPerPage - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		ArrayList<BoardDto> list = boardMapper.selectAll(startRow, endRow,category,sWord);

		// 데이터 전송 - list,maxPage,startPage,endPage,page
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("countAll", countAll);

		return map;
	}

	// 게시글 1개 가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevdto = boardMapper.selectOnePrev(bno);
		BoardDto nextdto = boardMapper.selectOneNext(bno);

		// 조회수 1증가
		boardMapper.bhitUp(bno);

		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevdto);
		map.put("nextdto", nextdto);

		return map;
	}

	// 게시글 저장
	@Override
	public void bInsert(BoardDto bdto) {
		int result = boardMapper.bInsert(bdto);
		System.out.println("BServiceImpl bInsert result " + result);
	}

	// 게시글 삭제
	@Override
	public void bDelete(int bno) {
		int result = boardMapper.bDelete(bno);
		System.out.println("BServiceImpl bDelete result " + result);

	}

	@Override
	public void doBUpdate(BoardDto bdto) {
		// 게시글 수정저장
		int result = boardMapper.doBUpdate(bdto);
		System.out.println("BServiceImpl bDelete result " + result);

	}

	@Override
	public void doBReply(BoardDto bdto) {
		// 답변달기 저장 bdto안에는 bgroup,bstep,bindent가 있음
		// 1. 부모보다 큰 bstep은 1씩 증가 시킴
		// 2. 현재 글은 부모bstep+ 1 저장
		// 3. bindent는 부모보다 1더하기
		// 4. bgroup은 부모와 같음.

		boardMapper.bstepUp(bdto);

		int result = boardMapper.doBReply(bdto);

	}

}
