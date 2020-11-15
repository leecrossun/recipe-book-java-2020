<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="<c:url value='/css/find.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/style.css' />"
	type="text/css">

<title>레시피 등록</title>
</head>
<body>
	<div class="container">
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
			<p class="mb-0">레시피 등록</p>
			<footer class="blockquote-footer">나만의 레시피를 등록해 보세여</footer>
		</blockquote>

		<form>
			<div class="form-group">
				<label for="exampleFormControlInput1">레시피 이름</label> 
				<input type="email" class="form-control" id="exampleFormControlInput1"
					placeholder="냠냠">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">레시피 한줄 설명</label> 
				<input type="email" class="form-control" id="exampleFormControlInput1"
					placeholder="냠냠">
			</div>
			<div class="form-group">
				<label for="exampleFormControlFile1">음식 사진</label> 
				<input type="file" class="form-control-file" id="exampleFormControlFile1">
			</div>

			<div class="form-group">
			<label for="exampleFormControlFile1">필요한 재료<br></label>
			<div class="input-group">
				<input type="text" class="form-control"
					placeholder="재료명 입력"
					aria-label="재료명 입력"
					aria-describedby="button-addon4">
				<div class="input-group-append" id="button-addon4">
					<button class="btn btn-outline-secondary" type="button">검색</button>
					<button class="btn btn-outline-secondary" type="button">등록</button>
				</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="exampleFormControlTextarea1">만드는 방법</label>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="3" placeholder="1. ... 2. ..."></textarea>
			</div>
		</form>
		
		<button type="button" class="mx-auto btn btn-outline-dark">레시피 등록</button>

	</div>
</body>
