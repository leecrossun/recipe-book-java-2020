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
<style>
.container {
	align-items: center !important;
}

.sub-container {
	border: none !important;
	display: flex;
	flex-direction: column;
	align-items: center !important;
	width: 90%;
}

/* Recipe  */
img {
	border-radius: 20px;
}

.recipe {
	display: flex;
	flex-direction: column;
	align-items: center;
}

/* Table */
table {
	border-collapse: collapse;
	border: 1px solid black;
	width: 90%;
}

tr, td {
	padding: 10px;
	text-align: center;
	border-bottom: 1px solid black;
}

/* Button  */
.button-box {
	display: inline !important;
	border: none !important;
	text-align: center;
	width: 200px;
}

.btn {
	padding: 5px 15px;
	font-size: 0.7em;
}

/* Form Area */
.form {
	border-radius: 10px;
}
</style>
</head>

<body>
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
	<div class="container">
		<p class="mainTitle">💜 레시피 등록 💜</p>
	</div>
	<div class="container">
		<br>
		<p class="mainTitle">🍧 나만의 레시피를 등록해보세요! 🍧</p>
		<br>
		<div class="sub-container" style="margin: 0px auto;">
			<form>
				<table>
					<tr>
						<td width="100px">레시피 이름</td>
						<td width="700px"><input type="text" class="form"
							style="width: 90%;" placeholder="레시피명을 적어주세요"></td>
					</tr>
					<tr>
						<td>한줄 설명</td>
						<td><input type="text" class="form" style="width: 90%;"
							placeholder="레시피를 한줄로 설명해주세요"></td>
					</tr>
					<tr>
						<td>음식 사진</td>
						<td><input type="file" class="form"></td>
					</tr>
					<tr>
						<td>필요한 재료</td>
						<td><input type="text" class="form" placeholder="재료명 입력"><a
							class="btn">검색</a><a class="btn">등록</a></td>
					</tr>
					<tr>
						<td>조리 과정</td>
						<td><textarea placeholder="1. ... 2. ..."
								style="width: 300px; height: 500px;" class="form"></textarea></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="button-box">
								<a class="btn">레시피 등록</a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
	<!-- 
	<form>
		<div class="form-group">
			<label for="exampleFormControlTextarea1">만드는 방법</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
				placeholder="1. ... 2. ..."></textarea>
		</div>
	</form>

	<button type="button" class="mx-auto btn btn-outline-dark">레시피 등록</button> -->
</body>