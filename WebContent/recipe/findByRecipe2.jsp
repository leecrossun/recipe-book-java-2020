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

	<script>
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}
	</script>
</head>

<body>
	<!-- Navigation Bar -->
	<div class="container nav" style="width: 100%;">
		<div class="w3-sidebar w3-bar-block w3-border-right" style="display:none; border-radius: 0px;" id="mySidebar">
			<button onclick="w3_close()" class="w3-bar-item w3-large">Close &times;</button>
			<c:if test="${sessionScope.userId eq null}">
				<a href="<c:url value='/user/login/form' />" class="w3-bar-item w3-button">로그인</a>
			</c:if>
			<c:if test="${sessionScope.userId ne null }">
				<a href="<c:url value='/user/logout' />" class="w3-bar-item w3-button">로그아웃하기</a>
				<a href="<c:url value='/user/update'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>" class="w3-bar-item w3-button">회원 수정</a>
				<a href="<c:url value='/refrigerator/view'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>" class="w3-bar-item w3-button">냉장고</a>
				<a href="<c:url value='/recipe/createForm'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>" class="w3-bar-item w3-button">레시피 쓰기</a>
				<a href="<c:url value='/user/delete'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>" class="w3-bar-item w3-button">회원 탈퇴</a>
			</c:if>
			<a href="#" class="w3-bar-item w3-button">Link 1</a>
			<a href="#" class="w3-bar-item w3-button">Link 2</a>
			<a href="#" class="w3-bar-item w3-button">Link 3</a>
		</div>
		<div style="width: 70px; float: left; background-color: rgb(226, 226, 226); border: none; border-radius: 10px;">
			<button class="w3-button w3-xlarge" onclick="w3_open()">☰</button>
		</div>
		<p class="logo">Recipe Book</p>
		<div class="searchContainer" style="border: none;">
			<input class="searchBar" type="text" placeholder="검색어 입력">
			<button class="searchBtn">검색</button>
		</div>
		<p class="menu">🍒 ${sessionScope.userId } 님 환영합니다</p>
	</div>
	<!-- TitleArea -->
	<div class="container">
		<p class="mainTitle">💜 요리명으로 검색 💜</p>
	</div>

	<!-- 검색 -->
	<!-- <div class="container mx-auto">
		<div class="searchContainer mx-auto" style="border: none;">
			<input class="searchBar" type="text" placeholder="요리명 입력">
			<button class="searchBtn2">검색</button>
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
									src="https://jhealthfile.joins.com/photo//2019/06/10/1341592187da3.jpg" alt="paris">
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
									src="https://gradium.co.kr/wp-content/uploads/taco.jpg" alt="paris">
							</a>
						</div>
					</article>
				</div>
			</div>
		</div>
	</div> -->

</body>

</html>