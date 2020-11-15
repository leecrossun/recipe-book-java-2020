<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="<c:url value='/css/find.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/style.css' />"
	type="text/css">

<title>레시피 상세페이지</title>
</head>
<body>
	<div class="container">
	
	<!-- Navigation Bar -->
	<div class="container nav">
		<div class="hamburger">
			<div id="wrapper">
				<div id="line-top" class="line"></div>
				<div id="line-mid" class="line"></div>
				<div id="line-bot" class="line"></div>
			</div>
		</div>
		<p class="logo">Recipe Book</p>
		<div class="searchContainer" style="border: none;">
			<input class="searchBar" type="text" placeholder="검색어 입력">
			<button class="searchBtn">검색</button>
		</div>
		<p class="menu">Somvengers 님</p>
		<p class="menu">🛒Refrigerator</p>
		<p class="menu">⚙ Settings</p>
	</div>
	
		<blockquote class="blockquote"
			style="margin-top: 3rem; margin-bottom: 3rem; margin-left: 1rem">
			<p class="mb-0">레시피 이름</p>
			<footer class="blockquote-footer"> 레시피 요약.. 어쩌구 저쩌구 </footer>
		</blockquote>

		<div class="row" style="margin-top: 20px">
			<div class="col-8">
				<img style="width: 600px; height: auto"
					src="https://images.otwojob.com/product/S/4/j/S4j16Sr3BY3cCiq.jpg/o2j/resize/852x622%3E"
					alt="사진" />
			</div>
			<div class="col-4">
				<ul class="list-unstyled">
					<li>준비물
						<form class="form-inline">
							<select class="custom-select my-1 mr-sm-2"
								id="inlineFormCustomSelectPref">
								<option selected>Choose...</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
							</select> <label class="my-1 mr-2" for="inlineFormCustomSelectPref">인분
								조리</label>
							<button type="submit" class="btn btn-light my-1">적용</button>
						</form>
						<ul>
							<li>사과 1개</li>
							<li>귤 5개</li>
							<li>당근 1개</li>
						</ul>
					</li>
					<li><br>요리방법
						<ol>
							<li>어쩌구</li>
							<li>어쩌구</li>
							<li>어쩌구</li>
						</ol></li>
				</ul>
			</div>
		</div>

		<button type="button" class="mx-auto btn btn-outline-dark">레시피
			저장</button>


		<form style="margin-top: 3rem">
			<p class="font-weight-normal">후기 작성</p>
			<div class="form-group">
				<label for="exampleFormControlInput1">아이디</label>
				<p class="font-weight-light">somvengers</p>
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">후기</label>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="3"></textarea>
			</div>
		</form>

		<button type="button" class="mx-auto btn btn-outline-dark">후기
			등록</button>
	</div>
</body>
</html>