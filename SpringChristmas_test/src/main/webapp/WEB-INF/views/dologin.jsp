<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>	
		<script>
			if(${result}==1) {
				alert("로그인 되었습니다.");
				location.href="/";
			}else {
				alert("아이디 혹은 패스워드가 잘못되었습니다. 다시 로그인 해주세요.")
				location.href="login";
			}
		</script>
	
	
	</body>
</html>