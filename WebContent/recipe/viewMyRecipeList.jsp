<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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