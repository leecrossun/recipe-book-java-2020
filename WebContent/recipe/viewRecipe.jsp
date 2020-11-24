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
<link rel="stylesheet" href="<c:url value='/css/viewRecipe.css' />"
	type="text/css">
<title>레시피 상세페이지</title>
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
	width: 40%;
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

	<!-- recipe Area -->
	<div class="container">
		<p class="mainTitle">💜 레시피 이름 💜</p>
	</div>
	<div class="container recipe">
		<br>
		<p class="mainTitle">🍧 레시피 요약 -------------------------- 한 레시피 🍧</p>
		<br>
		<div class="image sub-container">
			<img style="width: 600px; height: auto"
				src="https://images.otwojob.com/product/S/4/j/S4j16Sr3BY3cCiq.jpg/o2j/resize/852x622%3E"
				alt="사진" />
		</div>
		<br>
		<br>
		<div class="material sub-container">
			<p class="title">🥕 준비물</p>
			<br>
			<form>
				<select class="form">
					<option selected>몇인분?</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select> <label>인분 조리</label>
				<button type="submit" class="btn">적용</button>
			</form>
			<table>
				<th>재료명</th>
				<th width="150px">개수</th>
				<tr>
					<td>사과</td>
					<td>1 개</td>
				</tr>
				<tr>
					<td>귤</td>
					<td>5 개</td>
				</tr>
				<tr>
					<td>당근</td>
					<td>1 개</td>
				</tr>
			</table>
		</div>
		<br>
		<br>
		<div class="step sub-container">
			<p class="title">🥕 요리방법</p>
			<br>
			<table>
				<tr>
					<td width="150px">1</td>
					<td>당근을 잘게 다진다</td>
				</tr>
				<tr>
					<td>2</td>
					<td>-------------------------</td>
				</tr>
				<tr>
					<td>3</td>
					<td>-------------------------</td>
				</tr>
			</table>
		</div>
		<br>
		<br>
		<div class="button-box">
			<a class="btn">수정</a> <a class="btn">삭제</a>
		</div>
		<br>
		<br>
	</div>
	</div>
	<!-- Review Area -->
	<div class="container" style="align-items: center; text-align: center;">
		<div class="sub-container" style="margin: 0px auto;">
			<br>
			<br>
			<p class="title">🍰 후기 작성</p>
			<br>
			<form>
				⭐ 별점 <select class="form">
					<option selected>별점</option>
					<option value="1">⭐</option>
					<option value="2">⭐⭐</option>
					<option value="3">⭐⭐⭐</option>
					<option value="4">⭐⭐⭐⭐</option>
					<option value="5">⭐⭐⭐⭐⭐</option>
				</select> <br>
				<textarea placeholder="후기를 작성해주세요"
					style="width: 300px; height: 200px;" class="form"></textarea>
				<br>
				<div class="button-box">
					<a class="btn">후기 등록</a>
				</div>
			</form>
		</div>
	</div>
</body>

</html>