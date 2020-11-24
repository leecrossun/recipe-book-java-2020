<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!private String loginUserId; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function userDelete(){
		form.action ="<c:url value='/user/delete'/>";
		form.submit();
	}
</script>
</head>
<body>
<div class="container">  
	
	<%loginUserId = null;
	 if(session.getAttribute("userId") != null)
	 	loginUserId = (String)session.getAttribute("userId"); 
	 %>
	
	<p>현재 로그인한 회원: <%=loginUserId %></p><br>
	<a href="<c:url value='/user/login/form' />" class="btn btn-link">로그인 화면</a>
	<a href="<c:url value='/user/register/form' />" class="btn btn-link">회원 가입  화면</a> 
	<a href="<c:url value='/user/logout' />" class="btn btn-link">로그아웃하기</a> 

	 <!-- 원래는 이렇게 하고 싶었음 <a href="<c:url value='/user/update'>
						   <c:param name='userId' value='<%=loginUserId %>'/>
				 		 </c:url>">회원 수정</a>
				 		 
	-->		 
	<a href="<c:url value='/user/update'/>" class="btn btn-link">회원 탈퇴</a>	
	<div class="form-inline form-group"> 
	<form name="form" action="<c:url value='/user/update' />" method="GET">
		<input type="text" class = "form-control" name="userId" value="<%=loginUserId %>"}">
		<input type="submit" class="btn btn-primary" value="해당 회원정보 수정">
		<input type="button" class="btn btn-primary" value="회원 탈퇴" onClick="userDelete()">
	</form>
	</div>	
</div>
</body>
</html>