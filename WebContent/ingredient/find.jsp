<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/style.css' />" />
<style>
/* Table Area */
.table {
	width: 31%;
	margin-right: 18px;
}

.table>.title {
	font-size: 20px;
	text-align: center;
}

table {
	text-align: center;
	margin: 0px auto;
	border-collapse: collapse;
}

tr {
	border-bottom: 1px solid black;
}

td>div {
	height: 90%;
	width: 100px;
	border: none;
}
</style>
<script type="text/javascript">
	function sendChildValue(name) {
		opener.setChildValue(name);
		window.close();
	}
</script>
<title>재료 검색</title>
</head>
<body>

	<div class="container" style="width: 400px;">
		<div style="display: flex; flex-direction: row; border: none;">
			<form method="POST" action="<c:url value="/ingredient/find"/>">
				<input class="searchBar" type="text" name="keyword"
					value="${keyword}"
					style="border-radius: 20px; font-size: 12px; width: 100px;"
					placeholder="재료명 입력"> 
				<input type="submit"
					class="searchBtn" value="검색"></input>
			</form>
		</div>
		<div class="container" style="border: none; width: 400px;">
			<div class="table">
				<table>
					<th width="50px">재료목록</th>
					<c:forEach var="ingredient" items="${ingredientList}">
						<tr>
							<td><a href="javascript:sendChild">${ingredient.ingredientName}
								</a>
							</td>					
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

</body>
</html>