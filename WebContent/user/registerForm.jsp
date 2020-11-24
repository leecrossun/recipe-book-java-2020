<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">   
 
$(document).ready(function(){
    $('#selectEmail2').change(function(){
        var selectedText = $("#selectEmail2 option:selected").val();
        $("#email2").val(selectedText);
    })
});
            
function userCreate() {
     if (form.name.value == "") {
        alert("이름을 입력하십시오.");
        form.name.focus();
        return false;
     }
   
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

	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email1.value + "@" + form.email2.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(form.phone1.value + "-" + form.phone2.value + "-" + form.phone3.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone2.focus();
		return false;
	}
	form.submit();
}
</script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">회원가입창</div>
			<div class="panel-body">
			<form class="col-md-6 col-lg-6" name="form" method="POST" action="<c:url value='/user/register' />">
					<div class="form-group form-inline">
						<label for="name">이름 </label> 
						<input type="text" name="name" class="form-control" placeholder="이름을 입력해주세요" 	
						value="<c:if test="${registerFailed}">${user.name }</c:if>">
					</div>
					<div class="form-group form-inline">
						<label for="userId">아이디 </label> 
						<input type="text" name="userId" class="form-control" placeholder="아이디를 입력해주세요" 	
						value="<c:if test="${registerFailed}">${user.userId }</c:if>">
					</div>
					<div class="form-group form-inline">
						<label for="password">비밀번호 </label> <input type="password"
							name="password" class="form-control" placeholder="비빌번호를 입력해주세요">
					</div>
					
					<div class="form-group form-inline"><label>전화번호</label> : 
					<select	name="phone1" id="phone1" class="form-control">
							<option value="010">010</option>
							<option value="011">011</option>
					</select> - 
					<input name="phone2" type="text" class="form-control"> - 
					<input name="phone3" type="text" class="form-control">
					</div>
					<div class="form-group form-inline"><label>이메일</label> : 
						<input name="email1" type="text" class="form-control" placeholder="이메일">
						@ <input name="email2" id="email2" type="text" class="form-control"> 
						<select id="selectEmail2">
							<option value="">직접 입력</option>
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="hanmail.com">hanmail.com</option>
							<option value="daum.com">daum.com</option>
						</select>
					</div>
					<div class="form-group">
						<c:if test="${registerFailed}"><h6 class="text-danger"><c:out value="${exception.getMessage()}" /></h6></c:if>
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-primary" value="회원가입" onClick="userCreate()"> 
					</div>
				</form>
				
			</div>
		</div>


	</div>
</body>
</html>
