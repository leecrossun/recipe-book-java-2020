<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<style>
/* Hamburger Menu*/
.hamburger {
	width: 70px !important;
	height: 70px !important;
	background: rgb(199, 199, 199) !important;
	text-align: center !important;
}

.line {
	margin: 3px auto !important;
	background: #ffffff !important;
	width: 40px !important;
	height: 1px !important;
	border-radius: 3px !important;
}

#wrapper {
	margin: auto auto !important;
	border: none !important;
}
</style>
</head>
<!-- Navigation Bar -->
<div class="container nav" style="height: 110px;">
		<div class="hamburger" onclick="w3_open()">
			<div id="wrapper">
				<div id="line-top" class="line"></div>
				<div id="line-mid" class="line"></div>
				<div id="line-bot" class="line"></div>
			</div>
		</div>
	<div class="w3-sidebar w3-bar-block w3-border-right" style="display:none; border-radius: 0px;" id="mySidebar">
		<button onclick="w3_close()" class="w3-bar-item w3-large">Close &times;</button>
		<c:if test="${sessionScope.userId eq null}">
			<a href="<c:url value='/user/login/form' />" class="w3-bar-item w3-button">로그인</a>
		</c:if>
		<c:if test="${sessionScope.userId ne null }">
			<a href="<c:url value='/user/logout' />" class="w3-bar-item w3-button">로그아웃하기</a>
			<a href="<c:url value='/user/update'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>"
				class="w3-bar-item w3-button">회원 수정</a>
			<a href="<c:url value='/refrigerator/view'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>"
				class="w3-bar-item w3-button">냉장고</a>
			<a href="<c:url value='/recipe/createForm'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>"
				class="w3-bar-item w3-button">레시피 쓰기</a>
			<a href="<c:url value='/user/delete'> <c:param name='userId' value='${sessionScope.userId }'/> </c:url>"
				class="w3-bar-item w3-button">회원 탈퇴</a>
		</c:if>
	</div>
	<!-- 		<div style="width: 70px; float: left; background-color: rgb(226, 226, 226); border: none; border-radius: 10px;">
			<button class="w3-button w3-xlarge" onclick="w3_open()">☰</button>
		</div> -->
 	<p class="logo">Recipe Book</p>
	<div class="searchContainer" style="border: none;">
		<input class="searchBar" type="text" placeholder="레시피명 입력">
		<button class="searchBtn">검색</button>
	</div>
	<p class="menu">🍒 ${sessionScope.userId } 님 환영합니다</p>
</div>
<script>
	function w3_open() {
		document.getElementById("mySidebar").style.display = "block";
	}

	function w3_close() {
		document.getElementById("mySidebar").style.display = "none";
	}
</script>