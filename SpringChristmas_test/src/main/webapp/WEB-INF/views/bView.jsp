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
  <title>뷰페이지</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/read.css">
</head>
<body>
<section>
    <h1>NOTICE</h1>

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
        <td>조회수</td>
        <td>${map.bdto.bhit}</td>
      </tr>
      <tr>
        <td colspan="4" class="article">${map.bdto.bcontent}</td>
      </tr>
      <tr>
      	<td>파일이름</td>
      	<c:if test="${map.bdto.bfile==null}">
      		<td>파일이름이 없습니다.</td>
      	</c:if>
        <td colspan="3">${map.bdto.bfile}</td>
      </tr>
      <tr>
      	<td>이미지</td>
      	<c:if test="${map.bdto.bfile==null}">
      		<td>이미지가 존재하지 않습니다.</td>
      	</c:if>
        <td colspan="3"><img src="/upload/${map.bdto.bfile}"></td>
      </tr>
      <c:if test="${map.nextdto==null}">
      <tr>
      	<td colspan="3"><strong>다음글</strong>
      	<span class="separator">|</span>다음글이 없습니다.</td>
      </tr>
      </c:if>
      <c:if test="${map.nextdto!=null}">
        <td colspan="3"><strong>다음글</strong> 
        <span class="separator">|</span><a href="bView?bno=${map.nextdto.bno}"> ${map.nextdto.btitle}</a></td>
        </c:if>
        <c:if test="${map.prevdto==null}">
        <tr>
         <td colspan="3"><strong>이전글</strong> 
         <span class="separator">|</span>이전글이 없습니다.</td>
         </tr>
        </c:if>
        <c:if test="${map.prevdto!=null}">
      <tr>
        <td colspan="3"><strong>이전글</strong>
         <span class="separator">|</span><a href="bView?bno=${map.prevdto.bno}"> ${map.prevdto.btitle}</a></td>
      </tr>
      </c:if>
    </table>

    <a href="bList"><div class="list">목록</div></a>
    <a href="bDelete"><div class="list">삭제</div></a>
    <a href="bUpdate?bno=${map.bdto.bno}"><div class="list">수정</div></a>
    <a href="bReply"><div class="list">답변달기</div></a>
  </section>
</body>
</html>