<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="<c:url value='/css/find.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/style.css' />"
	type="text/css">

<title>Recipe Book</title>
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

		<div class="container" style="margin-top: 30px">
			<div class="row">
				<div class="col-3">
					<article id="3685" class="location-listing">
						<a class="location-title" href="#"> 첫번째 </a>
						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://images.otwojob.com/product/S/4/j/S4j16Sr3BY3cCiq.jpg/o2j/resize/852x622%3E"
								alt="san francisco">
							</a>
						</div>
					</article>
				</div>
				<div class="col-3">
					<article id="3688" class="location-listing">
						<a class="location-title" href="#"> 두번째 </a>
						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://dimg.donga.com/a/500/0/90/5/ugc/CDB/29STREET/Article/5e/b2/04/e8/5eb204e81752d2738236.jpg"
								alt="london">
							</a>
						</div>
					</article>
				</div>
				<div class="col-3">
					<article id="3691" class="location-listing">
						<a class="location-title" href="#"> 세번째 </a>
						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://img1.daumcdn.net/thumb/S600x434/?scode=1boon&fname=https://t1.daumcdn.net/liveboard/dailylife/3cfa947defa5429ba74d195c299070c9.jpg"
								alt="new york">
							</a>
						</div>
					</article>
				</div>
				<div class="col-3">
					<article id="3694" class="location-listing">
						<a class="location-title" href="#"> 네번째 </a>
						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/03/a0003495/img/basic/a0003495_main.jpg?20190823151731&q=80&rw=750&rh=536"
								alt="cape town">
							</a>
						</div>
					</article>
				</div>
			</div>
			<div class="row" style="margin-top: 10%">
				<div class="col-3">
					<article id="3697" class="location-listing">

						<a class="location-title" href="#"> 다섯번째 </a>

						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/XVPEGSXWHO257H4AOOOTOOAYRQ.jpg"
								alt="beijing">
							</a>
						</div>
					</article>
				</div>
				<div class="col-3">
					<article id="3700" class="location-listing">

						<a class="location-title" href="#"> 여섯번째 </a>

						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://jhealthfile.joins.com/photo//2019/06/10/1341592187da3.jpg"
								alt="paris">
							</a>
						</div>
					</article>
				</div>
				<div class="col-3">
					<article id="3703" class="location-listing">

						<a class="location-title" href="#"> 일곱번째 </a>

						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://health.chosun.com/site/data/img_dir/2020/06/26/2020062603299_0.jpg"
								alt="paris">
							</a>
						</div>
					</article>
				</div>
				<div class="col-3">
					<article id="3706" class="location-listing">

						<a class="location-title" href="#"> 여덟번째 </a>

						<div class="location-image">
							<a href="#"> <img width="300" height="169"
								src="https://gradium.co.kr/wp-content/uploads/taco.jpg"
								alt="paris">
							</a>
						</div>
					</article>
				</div>
			</div>
		</div>
	</div>

</body>
</html>