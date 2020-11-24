<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript">
	function login() {
		if (form.userId.value == "") {
			alert("사용자 ID를 입력하십시오.");
			form.userId.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}
		form.submit();
	}
</script>

</head>
<body>
<div id="headers"></div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">로그인 창</div>
			<div class="panel-body">
				<form class="col-md-6 col-lg-6" name="form" method="POST"
					action="<c:url value='/user/login' />">
					<div class="form-group form-inline">
						<label for="userId">아이디 </label> 
						<input type="text" name="userId" class="form-control" placeholder="아이디를 입력해주세요" 	
						value="<c:if test="${loginFailed}">${userId }</c:if>">
					</div>
					<div class="form-group form-inline">
						<label for="userId">비밀번호 </label> <input type="password"
							name="password" class="form-control" placeholder="비빌번호를 입력해주세요">
					</div>
					<div class="form-group">
						<c:if test="${loginFailed}"><h6 class="text-danger"><c:out value="${exception.getMessage()}" /></h6></c:if>
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-primary" value="로그인" onClick="login()"> 
						<a href="<c:url value='/user/register/form' />" class="btn btn-link">회원 가입 </a>
					</div>
				</form>
			</div>
		</div>

	</div>
</body>
</html>
