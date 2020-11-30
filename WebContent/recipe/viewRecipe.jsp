<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<c:url value='/css/find.css' />" type="text/css">
	<link rel="stylesheet" href="<c:url value='/css/style.css' />" type="text/css">
	<link rel="stylesheet" href="<c:url value='/css/viewRecipe.css' />" type="text/css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

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

		tr,
		td {
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

		/* Comment */
		.comment {
			display: flex;
			flex-direction: column;
			margin: 10px auto;
			background-color: rgb(241, 241, 241);
			border: none !important;
		}
	</style>
<script type="text/javascript">

function removeMsg() {
	return confirm("정말 삭제하시겠습니까?");		
}

function frmCheck() {
	var frm = document.form;

	if (frm.rating.value == '별점') {
		alert("별점을 입력하십시오.");
		frm.recipeName.focus();
		return false;
	}
	if (frm.content.value == "") {
				alert("내용을 입력하십시오.");
				frm.summary.focus();
				return false;
			}

			frm.submit();
		}
		
		</script>
</head>

<body>
	<!-- Navigation Bar -->
	<%@include file="../static/nav.jsp"%>

	<!-- recipe Area -->
	<div class="container">
		<p class="mainTitle">💜 레시피 이름 💜</p>
		<p class="mainTitle">💜 ${recipe.recipeName} 💜</p>
	</div>
	<div class="container recipe">
		<br>
		<!-- <p class="mainTitle">🍧 레시피 요약 -------------------------- 한 레시피 🍧</p> -->
		<p class="mainTitle">🍧 ${recipe.summary} 🍧</p>
		<br>
		<div class="image sub-container">
			<!-- <img style="width: 600px; height: auto"
				src="https://images.otwojob.com/product/S/4/j/S4j16Sr3BY3cCiq.jpg/o2j/resize/852x622%3E" alt="사진" /> -->
				<img style="width: 600px; height: auto" src="${recipe.image }" alt="${recipe.recipeName }">
		</div>
		<br>
		<br>
		<div class="material sub-container">
			<p class="title">🥕 준비물</p>
			<br>
			<form name="form" method="POST" action="<c:url value='/recipe/view'> <c:param name='recipeId' value='${recipe.recipeId}'/> </c:url>">
<!-- 				<select class="form" name="serving">
					<option selected>몇인분?</option>
					<option value="1" selected>1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select>-->
				<input type="text" name="serving" value="${servingString}"/> <label>인분 조리</label>
				<button type="submit" class="btn">적용</button>
			</form>
			<table>
				<tr>
					<th>재료명</th>
					<th width="150px">개수</th>
				</tr>
				<c:forEach var="ingredient" items="${rcpIng}">
					<tr>
						<td>${ingredient.ingredientName}</td>
						<td>
						<c:if test="${ingredient.amount ne 0}">
							<c:out value="${ingredient.amount}" />
						</c:if>
						 ${ingredient.unit}
						</td>
					</tr>
				</c:forEach>
<!-- 				<tr>
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
				</tr> -->
			</table>
		</div>
		<br>
		<br>
		<div class="step sub-container">
			<p class="title">🥕 요리방법</p>
			<br>
			<table>
				<c:forEach var="recipeStep" items="${rcpStep}" varStatus="st">
					<tr>
						<td width="150px">${st.count }번</td>
						<td>${recipeStep.content}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<br>
		<c:if test="${recipe.userId eq sessionScope.userId}">
		<div class="button-box">
		
			<a class="btn"
				href="<c:url value='/recipe/update'> <c:param name='recipeId' value='${recipe.recipeId}'/> </c:url>">수정</a>
			<!-- <a class="btn"
				href="<c:url value='/recipe/delete'> <c:param name='recipeId' value='${recipe.recipeId}'/> </c:url>">삭제</a> -->

			<a class="btn" href="<c:url value='/recipe/delete'>
               			<c:param name='recipeId' value='${recipe.recipeId }'/>
               			<c:param name='userId' value='${recipe.userId}'/>
               			</c:url>">삭제하기</a>			
		</div>
		</c:if>
		<br>
		<br>
	</div>

	<!-- Review Area -->
	<div class="container" style="align-items: center; text-align: center;">
		<div class="sub-container" style="margin: 0px auto;">
			<br>
			<br>
			
												
												
			<p class="title">🍰 후기 작성</p>
			<form class="servingSelect" name="form" method="POST" action="<c:url value='/review/create'><c:param name='recipeId' value='${recipe.recipeId}'/></c:url>">
				⭐ 별점 <select class="form" name="rating">
					<option selected>별점</option>
					<option value="1">⭐</option>
					<option value="2">⭐⭐</option>
					<option value="3">⭐⭐⭐</option>
					<option value="4">⭐⭐⭐⭐</option>
					<option value="5">⭐⭐⭐⭐⭐</option>
				</select><br><br>
				<textarea placeholder="후기를 작성해주세요" style="width: 700px; height: 70px;" class="form"
					name="content"></textarea>
				<br>
				<div class="button-box">
					<input class="btn" type="submit" onClick="frmCheck()" value="등록">
				</div>
			</form>
			
		</div>
<!-- 		<div class="comment">
			<p>작성자 : USER1</p>
			<p>별점 : ⭐⭐⭐⭐⭐</p>
			<p>TESTTESTTESTTESTTEST</p>
			<p>0000.00.00 작성</p>
			<p><a class="btn">수정</a><a class="btn">삭제</a></p>
		</div>
		<div class="comment">
			<p>작성자 : USER2</p>
			<p>별점 : ⭐⭐⭐⭐⭐</p>
			<p>TESTTESTTESTTESTTESTSTTEST</p>
			<p>0000.00.00 작성</p>
			<p><a class="btn">수정</a><a class="btn">삭제</a></p>
		</div> -->
		<c:forEach var="review" items="${reviews}">
			<div class="comment">
				<p>작성자 : ${review.userId} </p>
				<p>별점 :${review.rating}</p>
				<p>${review.content}</p>
				<p>${review.published} 작성</p>
				<p>
				<%-- <a class="btn" href="<c:url value='review/update'> <c:param name='recipe' value='&{recipe}'/> </c:url>">수정</a> --%>
				<c:if test="${review.userId eq sessionScope.userId}">
							<a class="btn" href="<c:url value='/review/delete'>
												<c:param name='reviewId' value='${review.reviewId}'/>
												<c:param name='recipeId' value='${recipe.recipeId}'/>
												</c:url>" onClick="return removeMsg()">삭제</a>
				</c:if>
				</p>
			</div>
		</c:forEach>
	</div>
</body>

</html>