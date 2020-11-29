<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<link rel="stylesheet" href="<c:url value='/css/find.css' />" type="text/css">
	<link rel="stylesheet" href="<c:url value='/css/style.css' />" type="text/css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

	<style>
		table tr, table td {
			border-bottom: 1px solid rgb(235, 235, 235);
		}
	</style>
</head>

<body>
	<!-- Navigation Bar -->
	<%@include file="../static/nav.jsp"%>

	<!-- List Area -->
	<div class="container">
		<p class="mainTitle">💜 My Recipe 💜</p>
		<p class="mainTitle"><a href="<c:url value='/recipe/createForm' />" class="btn" style="width: 200px; margin: 0px auto;">레시피 쓰기 </a></p>
	</div>
	<div class="container" style="height: 700px; margin:0px auto; overflow: auto; text-align: center;">
		<table style="width:70%;">
			<tr style="background-color: rgb(235, 235, 235);">
				<th width="150px">레시피이름</th>
				<th width="500px">레시피요약</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="myRecipe" items="${myRecipeList}">
				<tr>
					<td><a href="<c:url value='/recipe/view'>
               			<c:param name='recipeId' value='${myRecipe.recipeId }'/>
               			</c:url>">${myRecipe.recipeName}</a></td>
					<td>${myRecipe.summary}</td>

					<td width="100px"><a href="<c:url value='/recipe/update'>
               			<c:param name='recipeId' value='${myRecipe.recipeId }'/>
               			</c:url>">수정하기</a></td>
               			
               		<td width="100px"><a href="<c:url value='/recipe/delete'>
               			<c:param name='recipeId' value='${myRecipe.recipeId }'/>
               			<c:param name='userId' value='${myRecipe.userId}'/>
               			</c:url>">삭제하기</a></td>
				<tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>