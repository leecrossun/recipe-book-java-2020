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
            $(document).ready( function() {
            $("#headers").load("./loginHeader.jsp");              
            }); 
            
            
            $(document).ready(function(){
                $('#selectEmail2').change(function(){
                    var selectedText = $("#selectEmail2 option:selected").val();
                    $("#email2").val(selectedText);
                })
            })
            </script>
</head>
<body>
	<div id="headers"></div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">회원가입창</div>
			<div class="panel-body">
				<ul>
					<li class="form-inline"><label>이름</label> : <input type="text"
						class="form-control" placeholder="이름을 입력해주세요"></li>
					<br />
					<li class="form-inline"><label>아이디</label> : <input
						type="text" class="form-control" placeholder="아이디를 입력해주세요">
					</li>
					<br />
					<li class="form-inline"><label>비밀번호</label> : <input
						type="password" class="form-control" placeholder="비빌번호를 입력해주세요">
					</li>
					<br />
					<li class="form-inline"><label>전화번호</label> : <select
						name="phone1" id="phone1" class="form-control">
							<option value="010">010</option>
							<option value="011">011</option>
					</select> - <input type="text" class="form-control"> - <input
						type="text" class="form-control"></li>
					<br />
					<li class="form-inline"><label>이메일</label> : <input
						name="email1" type="text" class="form-control" placeholder="이메일">
						@ <input name="email2" id="email2" type="text"
						class="form-control"> <select id="selectEmail2">
							<option value="">직접 입력</option>
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="hanmail.com">hanmail.com</option>
					</select></li>
					<br />
				</ul>
				<button class="btn btn-info" style="float: right;">회원가입</button>
			</div>
		</div>


	</div>
</body>
</html>
