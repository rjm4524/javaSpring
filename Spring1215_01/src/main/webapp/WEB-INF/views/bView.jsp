<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>뷰페이지</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/read.css">
</head>
<script>
	$(function(){
		$("#u_btn").click(function(){
			alert("수정페이지로 이동합니다.")
			u_frm.submit();
		});
	});
</script>
<body>
	<section>
		<h1>NOTICE</h1>
		<form name="u_frm" action="bUpdate" method="post">
		<input type="hidden" name="bno" value="${bdto.bno}">
		<input type="hidden" name="btitle" value="${bdto.btitle}">
		<input type="hidden" name="id" value="${bdto.id}">
		<input type="hidden" name="bhit" value="${bdto.bhit}">
		<input type="hidden" name="bcontent" value="${bdto.bcontent}">
		<input type="hidden" name="bfile" value="${bdto.bfile}">
			<table>
				<colgroup>
					<col width="15%">
					<col width="50%">
					<col width="15%">
					<col width="20%">
				</colgroup>
				<tr>
					<th><strong>글번호</strong><span class="separator">|</span></th>
					<td colspan="3">${bdto.bno}</td>
				</tr>
				<tr>
					<th><strong>제목</strong><span class="separator">|</span></th>
					<td colspan="3">${bdto.btitle}</td>
				</tr>
				<tr>
					<td><strong>작성자</strong><span class="separator">|</span></td>
					<td>${bdto.id}</td>
					<td>조회수</td>
					<td>${bdto.bhit}</td>
				</tr>
				<tr>
					<td colspan="4" class="article"><strong>내용</strong><span
						class="separator">|</span>${bdto.bcontent}</td>
				</tr>
				<tr>
					<td colspan="4" class="article"><strong>파일</strong><span
						class="separator">|</span>${bdto.bfile}</td>
				</tr>
				<tr>
					<td><strong>다음글</strong> <span class="separator">|</span></td>
					<td colspan="3">[키즈잼] 2월 프로그램 안내</td>
				</tr>
				<tr>
					<td><strong>이전글</strong> <span class="separator">|</span></td>
					<td colspan="3">[키즈잼] 2020년 1분기 정기 휴관일 안내</td>
				</tr>
			</table>

			<a href=""><div class="list">목록</div></a>
			<div class="list">삭제</div>
			<div class="list" id="u_btn">수정</div>
			<a href=""><div class="list">답변달기</div></a>
		</form>
	</section>
</body>
</html>