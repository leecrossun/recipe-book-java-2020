<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--헤더 부분-->
	<div class="header">
		<h1>
			<span>Recipe</span>
		</h1>
		<img width="70" height="70"
			src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Open_book_nae_02.svg/250px-Open_book_nae_02.svg.png"
			alt="로고 이미지" />
	</div>
	${user.name }
	${user.password }
	<a href="/user/register/form">회원가입</a>
	
</body>
</html>