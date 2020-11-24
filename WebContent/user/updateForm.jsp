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
            
function userUpdate() {
     if (form.name.value == "") {
        alert("이름을 입력하십시오.");
        form.name.focus();
        return false;
     }
   
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}

	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}\d{3,4}\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	form.submit();
}
</script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">회원수정</div>
			<div class="panel-body">
			<form class="col-md-6 col-lg-6" name="form" method="POST" action="<c:url value='/user/update' />">
					<div class="form-group form-inline">
						<label for="name">이름 </label> 
						<input type="text" name="name" class="form-control" placeholder="이름을 입력해주세요" value="${user.name }">
					</div>
					<div class="form-group form-inline">
						<label for="userId">아이디 </label> 
						<input type="text" name="userId" class="form-control" value="${user.userId}" readonly>
					</div>
					<div class="form-group form-inline">
						<label for="password">비밀번호 </label> <input type="password"
							name="password" class="form-control" placeholder="비빌번호를 입력해주세요" value="${user.password }">
					</div>
					
					<div class="form-group form-inline"><label>전화번호</label> : 
					<input name="phone" type="text" class="form-control" value="${user.phone }"> 
					</div>
					<div class="form-group form-inline"><label>이메일</label> : 
						<input name="email" type="text" class="form-control" placeholder="이메일" value="${user.email}">
						
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-primary" value="기존회원수정" onClick="userUpdate()"> 
					</div>
				</form>
				
			</div>
		</div>


	</div>
</body>
</html>
