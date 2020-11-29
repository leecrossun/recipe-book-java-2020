<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="<c:url value='/css/find.css' />" type="text/css">
	<link rel="stylesheet" href="<c:url value='/css/style.css' />" type="text/css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

	<title>Recipe Book</title>

	<style>
		.btn {
			text-align: center;
			width: 100px;
			height: 100px;
			margin: 3px;
			padding: 5px;
			border: 1px solid black;
			color: rgb(0, 0, 0);
			font-size: 10pt;
		}

		.btn:hover {
			background-color: rgb(177, 53, 248);
			color: black;
		}

		#columns {
			display: grid;
			grid-gap: 10px;
			grid-template-rows: repeat(2, 1fr);
			grid-template-columns: repeat(5, 1fr);
			padding: 10px;
		}

		#columns .figure {
			display: inline-block;
			border: 1px solid rgba(0, 0, 0, 0.2);
			margin: 0;
			margin-bottom: 15px;
			padding: 10px;
			box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
		}

		#columns .figure img {
			width: 100%;
		}

		#columns .figure figcaption {
			border-top: 1px solid rgba(0, 0, 0, 0.2);
			padding: 10px;
			margin-top: 11px;
		}
	</style>
</head>

<body>
	<!-- Navigation Bar -->
	<%@include file="../static/nav.jsp"%>

	<!-- TitleArea -->
	<div class="container">
		<div class="container" style=" width:100%;">
			<div class="table" style="width:100%; border: none; margin: 0px auto;">
				<p class="title">추가할 재료 입력</p>
				<form name="form"
					action="<c:url value='/refrigerator/add' />">
				  <table id="table" border="1">
						<tr>
						   <th width="300px">재료명</th>
						</tr>
					</table>
					<input type="submit" class="btn" value="재료 선택 완료">
				</form>
					<div>
					ingredientName :<input type="text" name="fIngredientName" id="fIngredientName">
					<input type=button class=searchBtn value='검색' onClick=openWin();>
					<button onclick="addHtmlTableRow();">Add</button>
					<button onclick="editHtmlTbleSelectedRow();">Edit</button>
					<button onclick="removeSelectedRow();">Remove</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<p class="mainTitle">💜 검색 결과 💜</p>
	</div>
	<div id="columns">
		<c:forEach var="recipe" items="${SearchedRcps }">
		<div class="figure">
			<img src="${recipe.image }" alt="${recipe.recipeName }">
			<figcaption>${recipe.recipeName }</figcaption>
			<p class="summary">${recipe.summary }</p>
			<hr>
			<div style="text-align: right;">
				<a class="btn" href="<c:url value='/recipe/view'>
											<c:param name='recipeId' value='${recipe.recipeId}'/>
											</c:url>">MORE</a>
				<br>
			</div>
		</div>
		</c:forEach>
		</div>

</body>

</html>