<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" />
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function () {
			$('#selectEmail2').change(function () {
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
			if (emailExp.test(form.email1.value + "@" + form.email2.value) == false) {
				alert("이메일 형식이 올바르지 않습니다.");
				form.email.focus();
				return false;
			}
			var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
			if (phoneExp.test(form.phone1.value + "-" + form.phone2.value + "-" + form.phone3.value) == false) {
				alert("전화번호 형식이 올바르지 않습니다.");
				form.phone2.focus();
				return false;
			}
			form.submit();
		};
	</script>
	<style>
		/* Login */
		.login {
			width: 70%;
			border: none !important;
			display: flex;
			flex-direction: row;
			margin: 5px auto;
		}

		.registerForm {
			width: 200px;
			border: 1px solid black;
			box-shadow: 2px 2px black;
			background-color: #d8d8d8;
			color: #F5740A;
			text-align: center;
			margin: 0px 10px;
			height: 30px;
			border-radius: 10px;
		}

		.registerBtn {
			width: 100%;
			border: 1px solid black;
			text-decoration: none !important;
			color: #F5740A;
			background-color: white;
			text-align: center;
		}

		.registerBtn:hover {
			background-color: #B7F53B;
			color: #F5740A;
			/* color: black; */
		}

		input.registerBtn {
			margin: 5px;
		}

		input::placeholder {
			color: #F5740A;
			font-style: italic;
			font-size: 0.9em;
		}

		/* Placeholder */
		input:focus::-webkit-input-placeholder,
		textarea:focus::-webkit-input-placeholder {
			/* WebKit browsers */
			color: transparent !important;
		}

		input:focus:-moz-placeholder,
		textarea:focus:-moz-placeholder {
			/* Mozilla Firefox 4 to 18 */
			color: transparent !important;
		}

		input:focus::-moz-placeholder,
		textarea:focus::-moz-placeholder {
			/* Mozilla Firefox 19+ */
			color: transparent !important;
		}

		input:focus:-ms-input-placeholder,
		textarea:focus:-ms-input-placeholder {
			/* Internet Explorer 10+ */
			color: transparent !important;
		}
	</style>
</head>

<body>
	<!-- Navigation Bar -->
	<%@include file="../static/nav.jsp"%>

	<!-- Register Area -->
	<form name="form" class="container" style="width: 100%;" method="POST" action="<c:url value='/user/register' />">
		<br><br>
		<div style=" display: flex; flex-direction: column; margin: 0px auto;">
			<div style="font-size: 2em; margin: 10px auto; text-align: center; border: none;">
				회원가입
			</div>
			<div class="login">
				이름 &nbsp;&nbsp; <input type="text" name="name" class="registerForm" placeholder="이름을 입력해주세요"
					value="<c:if test=" ${registerFailed}">${user.name }</c:if>">
			</div>
			<div class="login">
				아이디 &nbsp;&nbsp; <input type="text" name="userId" class="registerForm" placeholder="아이디를 입력해주세요"
					value="<c:if test=" ${registerFailed}">${user.userId }</c:if>">
			</div>
			<div class="login">
				비밀번호 &nbsp;&nbsp; <input type="password" name="password" class="registerForm"
					placeholder="비밀번호를 입력해주세요">
			</div>
			<div class="login">
				이메일 &nbsp;&nbsp; <input name="email1" type="text" class="registerForm" placeholder="이메일">
				@ <input name="email2" id="email2" type="text" class="registerForm" style="width: 120px;">
				<select id="selectEmail2" class="registerForm" style="width: 100px;">
					<option value="">직접 입력</option>
					<option value="naver.com">naver.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="hanmail.com">hanmail.com</option>
					<option value="daum.com">daum.com</option>
				</select>
			</div>
			<div class="login">
				전화번호 &nbsp;&nbsp; <select name="phone1" id="phone1" class="registerForm" style="width: 100px;">
					<option value="010">010</option>
					<option value="011">011</option>
				</select> -
				<input name="phone2" type="text" class="registerForm" style="width: 100px;"> -
				<input name="phone3" type="text" class="registerForm" style="width: 100px;">
			</div>
			<div class="login">
				<c:if test="${registerFailed}">
					<h6 class="text-danger">
						<c:out value="${exception.getMessage()}" />
					</h6>
				</c:if>
			</div>
			<div class="login">
				<input type="button" class="registerBtn" value="회원가입" onClick="userCreate()">
			</div>
			<br>
		</div>
		<br>
	</form>
	
	<!-- <div class="container">
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


	</div> -->
<!-- 	<script>
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}
	</script> -->
</body>

</html>