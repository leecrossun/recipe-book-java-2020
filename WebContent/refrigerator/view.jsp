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
<style>

/* Ingredients Area */
.ingredients {
	
}

.ingredients>* {
	
}

.expired {
	
}

.mine {
	
}

/* Recipe Area */
.recipe {
	
}

.favorite {
	
}

.review {
	
}

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
				<%--
			
				<c:forEach var="ingredient" items="${userIngredient}">
					<p class="list">
						🤍 ${ingredient.ingredientName} >> ${ingredient.amout}${ingredient.unit} &#40; ${ingredient.expireDate} &#41; 
						<a class="btn" href="<c:url value='/refrigerator/deleteIngredient'>
												<c:param name='ingId' value='${ingredient.ingredientId}'/>
												</c:url>">Delete</a> 🤍
					</p>
			 	--%>
				<p class="list">
					🤍 당근 >> 100g &#40; 2020/12/15 &#41; <a class="btn">Delete</a> 🤍
				</p>
				<p class="list">
					🤍 양파 >> 50g &#40; 2020/11/10 &#41; <a class="btn">Delete</a> 🤍
				</p>
				<p class="list">
					🤍 표고버섯 >> 70g &#40; 2020/12/11 &#41; <a class="btn">Delete</a>🤍
				</p>
				<br>
				<p class="list" style="text-align: center; border: 1px solid black;">
					<a
						href="<c:url value='/refrigerator/addIngredient'>
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
				<%--
				<c:forEach var="favorite" items="${favorites}">
					<p class="list">🤍 ${favorite.recipeName} >> ${favorite.summary} 🤍</p>
				 --%>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
			</div>
			<div class="mine">
				<p class="title">📜 My Recipe</p>
				<%--
				<c:forEach var="myRecipe" items="${myRecipes}">
					<p class="list">🤍 ${myRecipe.recipeName} >> ${myRecipe.summary} 🤍</p>
				 --%>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
				<p class="list">🤍 Recipe Name >> Description 🤍</p>
			</div>
			<div class="review">
				<%--
				<c:forEach var="myReview" items="${myReviews}">
					<p class="list">🤍 ${myReview.recipeName} >> ${myRecipe.rating} 🤍</p>
				 --%>
				<p class="title">✍ My Review</p>
				<p class="list">🤍 Recipe Name >> ⭐⭐⭐ 🤍</p>
				<p class="list">🤍 Recipe Name >> ⭐⭐⭐⭐⭐ 🤍</p>
			</div>
		</div>
	</div>
</body>

</html>