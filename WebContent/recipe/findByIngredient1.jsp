<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Recipe Book</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/style.css' />" />
</head>
<style>
</style>
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
	<!-- TitleArea -->
	<div class="container">
		<p class="mainTitle">💜 재료명으로 검색 💜</p>
	</div>


	<!-- 검색 -->
	<div class="container mx-auto">
		<div class="searchContainer mx-auto" style="border: none;">
			<input class="searchBar" type="text" placeholder="재료명 입력">
			<button class="searchBtn2">재료 검색</button>
		</div>
		<div class="center-block">
		<button type="submit" class="mx-auto btn-outline-dark"
			style="margin-bottom: 10rem"
			onClick="location.href='/UserMan3/user/findByIngredient2.jsp'">검색한
			재료대로 레시피 검색</button>
		</div>
		<p class="text-center" style="margin-top: 10rem">아직 검색한 내용이 없어요!</p>
	</div>
</body>

</html>