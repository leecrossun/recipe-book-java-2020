<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#headers").load("loginHeader.jsp");
	});
</script>

</head>
<body>
	<div id="headers"></div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">로그인 창</div>
			<div class="panel-body">
				<ul>
					<br />
					<li class="form-inline"><label>아이디</label> : <input
						type="text" class="form-control" placeholder="아이디를 입력해주세요">
					</li>
					<br />
					<li class="form-inline"><label>비밀번호</label> : <input
						type="password" class="form-control" placeholder="비빌번호를 입력해주세요">
					</li>
					<br />

				</ul>
				<div style="float: right;">
					<button class="btn btn-info">로그인</button>
					<button class="btn btn-info">
						<a href="registerForm.html">회원가입</a>
					</button>
				</div>

			</div>
		</div>

	</div>
</body>
</html>
