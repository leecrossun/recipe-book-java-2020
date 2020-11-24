<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Recipe Book</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/style.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/find.css' />" />
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
		<p class="mainTitle">💜 요리명으로 검색 💜</p>
	</div>


	<!-- 검색 -->
	<div class="container">
		<div class="searchContainer" style="border: none;">
			<input class="searchBar" type="text" placeholder="요리명 입력">
			<button class="searchBtn"
				onClick="location.href='/RecipeBook/recipe/findByRecipe2.jsp'">검색</button>
		</div>
		<p class="text-center" style="margin-top: 10rem">아직 검색한 내용이 없어요!</p>
	</div>
</body>

</html>