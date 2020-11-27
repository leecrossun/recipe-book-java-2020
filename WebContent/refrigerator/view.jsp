<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Recipe Book</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" />
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.css"/> -->
    
	<style>
		/* List */
		.list {
			border-radius: 1em;
			padding: 7px;
		}

		.list:hover {
			background-color: lightgray;
		}

		.ingredients>.list {
			border: 1px solid black;
			text-align: center;
		}
	</style>
</head>

<body>
	<!-- Navigation Bar -->
	<%@include file="../static/nav.jsp"%>

	<!-- TitleArea -->
	<div class="container">
		<p class="mainTitle">💜 Somvengers 님의 냉장고 입니다 💜</p>
	</div>
	<!-- Ingredients Area -->
	<div class="container">
		<div class="ingredients">
			<p class="list">
				<a>🔎 Searching by My Ingredients</a>
			</p>
			<div class="expired">
				<p class="title">⏰ Expired Ingredients</p>
				<p class="list">
					🤍 우유 >> 500ml &#40; 2020/12/15 &#41; <a class="btn">Delete</a> 🤍
				</p>
				<p class="list">
					🤍 두부 >> 150g &#40; 2020/12/15 &#41; <a class="btn">Delete</a> 🤍
				</p>
			</div>
			<div class="mine">
				<p class="title">🥕 My Ingredients</p>
				<c:forEach var="ingredient" items="${userIngredient}">
					<p class="list">
						🤍 ${ingredient.ingredientName} >> ${ingredient.amount}${ingredient.unit} &#40;
						${ingredient.expireDate} &#41;
						<a class="btn" href="<c:url value='/refrigerator/deleteIngredient'>
												<c:param name='ingId' value='${ingredient.ingredientId}'/>
												</c:url>">Delete</a> 🤍
					</p>
			 	</c:forEach>
				<br>
				<p class="list" style="text-align: center; border: 1px solid black;">
					<a
						href="<c:url value='/refrigerator/selectIngredient'>
								<c:param name='userId' value='${user.userId}'/>
								</c:url>">
						➕ </a>
				</p>
			</div>
		</div>
		<!-- Recipe Area -->
		<div class="recipe">
			<div class="favorite">
				<p class="title">📌 Favorite Recipe</p>

				<c:forEach var="favorite" items="${favorites}">
					<a class="list" href="<c:url value='/recipe/view'>
											<c:param name='recipeId' value='${favorite.recipeId}'/>
											</c:url>" >🤍 ${favorite.recipeName} >> ${favorite.summary} 🤍</a><br><br>
				</c:forEach>
				<!-- <p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p> -->
			</div>
			<div class="mine">
				<p class="title">📜 My Recipe</p>
				<a href="<c:url value='/recipe/myList'>
               <c:param name='userId' value='${sessionScope.userId }'/>
               </c:url>">더보기</a>
				<c:forEach var="myRecipe" items="${myRecipes}">
				<a class="list" href="<c:url value='/recipe/view'>
               			<c:param name='recipeId' value='${myRecipe.recipeId}'/>
               			</c:url>">🤍 ${myRecipe.recipeName} >> ${myRecipe.summary} 🤍</a><br><br>
				</c:forEach>
				<!-- <p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p> -->
			</div>
			<div class="review">

				<p class="title">✍ My Review</p>
				<c:forEach var="myReview" items="${myReviews}">
					<a class="list">🤍 ${myReview.recipeName} >> ${myRecipe.rating} 🤍</a>
				</c:forEach>

				<!-- <p class="list">🤍 Recipe Name >> ⭐⭐⭐ 🤍</p>
				<p class="list">🤍 Recipe Name >> ⭐⭐⭐⭐⭐ 🤍</p> -->
			</div>
		</div>
	</div>
	<script>
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}
	</script>
</body>

</html>