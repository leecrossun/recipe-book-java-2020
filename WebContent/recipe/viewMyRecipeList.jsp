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
</head>
<body>
	<!-- Navigation Bar -->
	<%@include file="../static/nav.jsp"%>
	
	<!-- List Area -->
	<table>
		<tr>
			<th>레시피이름</th><th>레시피요약</th>
		</tr>
		<c:forEach var="myRecipe" items="${myRecipeList}">
				<tr>
					<td><a href="<c:url value='/recipe/view'>
               			<c:param name='recipeId' value='${myRecipe.recipeId }'/>
               			</c:url>">${myRecipe.recipeName}</a></td>
					<td>${myRecipe.summary}</td>
					
					<td><a href="<c:url value='/recipe/update'>
               			<c:param name='recipeId' value='${myRecipe.recipeId }'/>
               			</c:url>">수정하기</a></td>
				<tr>
		</c:forEach> 
	</table>
	<a href="<c:url value='/recipe/createForm' />">레시피 쓰기 </a>
</body>
</html>