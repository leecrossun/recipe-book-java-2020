<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Insert title here</title>
		<link rel="stylesheet" href="<c:url value='/css/style.css' />" type="text/css">
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
	<style>
		@import url('https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap');

		/* Navigation */
		.nav {
			display: block;
			height: 90px;
			text-align: center;
		}

		.nav>.hamburger {
			position: absolute;
			top: 35px;
			left: 40px;
		}

		.nav>.menu {
			display: inline;
			position: relative;
			top: 15px;
			left: 50px;
		}

		.nav>.logo {
			display: inline;
			position: relative;
			top: 15px;
			left: 0px;
			font-size: 5em;
			font-family: 'Lobster', cursive;
		}

		/* Login */
		.login {
			width: 70%;
			border: none !important;
			display: flex;
			flex-direction: row;
			margin: 0px auto;
		}

		.loginForm {
			width: 70%;
			border: 1px solid black;
			box-shadow: 2px 2px black;
			background-color: #F5740A;
			color: #B7F53B;
			text-align: center;
		}

		.loginBtn {
			width: 100%;
			border: 1px solid black;
			text-decoration: none !important;
			color: #F5740A;
			background-color: white;
			text-align: center;
		}

		.loginBtn:hover {
			background-color: #B7F53B;
			color: #F5740A;
			/* color: black; */
		}

		input.loginBtn {
			margin: 5px;
		}

		input::placeholder {
			color: #B7F53B;
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
	<br><br>
	<div class="nav" style="font-family:'Lobster', cursive;">
		<p class="logo">Recipe Book</p>
	</div>
	<br><br>
	<form name="form" class="container" style="width: 30%; display: flex; flex-direction: column;" method="POST"
		action="<c:url value='/user/login' />">
		<br>
		<div class="login">
			아이디 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="userId" class="loginForm"
				placeholder="아이디 입력" value="<c:if test=" ${loginFailed}">${userId }</c:if>">
		</div>
		<div class="login">
			비밀번호 &nbsp;&nbsp; <input type="password" name="password" class="loginForm" placeholder="비밀번호 입력">
		</div>
		<div class="login">
			<input type="button" class="loginBtn" value="로그인" onClick="login()">
		</div>
		<div class="login">
			<a class="loginBtn" href="<c:url value='/user/register/form' />">회원 가입 </a>
		</div>
		<br>
	</form>
	<!-- <div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">로그인 창</div>
			<div class="panel-body">
				<form class="col-md-6 col-lg-6" name="form" method="POST" action="<c:url value='/user/login' />">
					<div class="form-group form-inline">
						<label for="userId">아이디 </label>
						<input type="text" name="userId" class="form-control" placeholder="아이디를 입력해주세요"
							value="<c:if test=" ${loginFailed}">${userId }</c:if>">
					</div>
					<div class="form-group form-inline">
						<label for="userId">비밀번호 </label> <input type="password" name="password" class="form-control"
							placeholder="비빌번호를 입력해주세요">
					</div>
					<div class="form-group">
						<c:if test="${loginFailed}">
							<h6 class="text-danger">
								<c:out value="${exception.getMessage()}" />
							</h6>
						</c:if>
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-primary" value="로그인" onClick="login()">
						<a href="<c:url value='/user/register/form' />" class="btn btn-link">회원 가입 </a>
					</div>
				</form>
			</div>
		</div> -->

	</div>
</body>

</html>