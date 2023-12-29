<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <title>뷰페이지</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/read.css">
</head>
<body>
<section>
    <h1>NOTICE</h1>
	<form action="bUpdate" name="bFrm" method="post">
	<input type="hidden" name="bno" value="">
	<input type="hidden" name="btitle" value="">
	<input type="hidden" name="bcontent" value="">
	<input type="hidden" name="id" value="">
	<input type="hidden" name="bhit" value="">
	<input type="hidden" name="bfile" value="">
    <table>
      <colgroup>
        <col width="15%">
        <col width="50%">
        <col width="15%">
        <col width="20%">
       </colgroup>
      <tr>
      	<th><strong>글번호</strong></th>
        <th colspan="3">${map.bdto.bno}</th>
      </tr>
      <tr>
      	<td><strong>제목</strong></td>
        <td colspan="3">${map.bdto.btitle}</td>
      </tr>
      <tr>
        <td><strong>작성자</strong></td>
        <td>${map.bdto.id}</td>
        <td><strong>조회수</strong></td>
        <td>${map.bdto.bhit}</td>
      </tr>
      <tr>
        <td colspan="4" class="article">${map.bdto.bcontent}</td>
      </tr>
      
      <c:set var="imgNameArr" value="${fn:split(map.bdto.bfile,',') }" />
      
      <c:forEach var="i" items="${imgNameArr}" varStatus="vs">
      <tr>
      	<td><strong>파일이름${vs.count}</strong></td>
        <td colspan="3">${i}</td>
      </tr>
      <tr>
      	<td><strong>이미지${vs.count}</strong></td>
        <td colspan="3"><img src="/upload/${i}"></td>
      </tr>
      </c:forEach>
      
      <c:if test="${map.nextdto.bno!=null}">
      <tr>
      	<td><strong>다음글</strong></td>
        <td colspan="3"><a href="bView?bno=${map.nextdto.bno}">${map.nextdto.btitle}</a></td>
      </tr>
      </c:if>
      <c:if test="${map.nextdto.bno==null}">
      <tr>
      	<td><strong>다음글</strong></td>
        <td colspan="3">다음글이 존재하지 않습니다.</td>
      </tr>
      </c:if>
      <c:if test="${map.prevdto.bno!=null}">
      <tr>
      	<td><strong>이전글</strong></td>
        <td colspan="3"><a href="bView?bno=${map.prevdto.bno}">${map.prevdto.btitle}</a></td>
      </tr>
      </c:if>
      <c:if test="${map.prevdto.bno==null}">
      <tr>
      	<td><strong>이전글</strong></td>
        <td colspan="3">이전글이 존재하지 않습니다.</td>
      </tr>
      </c:if>
    </table>
	<script>
		$(function(){
			$("#bupdateBtn").click(function(){
				alert("수정페이지로 이동합니다.");
				bFrm.submit();
			})
		});
	
	</script>


    <a href="bList"><div class="list">목록</div></a>
    <a href=""><div class="list">삭제</div></a>
    <div class="list" id="bupdateBtn">수정</div>
    <a href=""><div class="list">답변달기</div></a>
    </form>
  </section>
</body>
</html>