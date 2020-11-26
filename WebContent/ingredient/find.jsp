<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" />
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
	<div class="container" style="border: none;">
		<p class="mainTitle">💜 재료 검색 💜</p>
	</div>
	<div class="container" style="height: 100%; text-align: center; border: none;">
		<div style="border: none; margin: 0px auto; ">
			<form style="display: flex; flex-direction: row; border: none; margin: 0px auto;" action="<c:url value="/ingredient/find"/>"> 
				<input class="searchBar" type="text" name="keyword" value="${keyword}" style="border: 1px solid black; font-size: 12px; width: 90%;" placeholder="재료명 입력">
				<input type="submit" class="searchBtn" value="검색"></input>
			</form>
		</div>
		<div class="container" style="border: none; width: 400px;">
			<div class="table" style="margin: 0px auto; border: none; width: 100%;">
				<table>
					<th width="200px" style="background-color: rgb(233, 233, 233);">재료목록</th>
					<c:forEach var="ingredient" items="${ingredientList}">
						<tr>
							<td><a href="javascript:sendChildValue('${ingredient.ingredientName}')">
									${ingredient.ingredientName} </a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

</body>

</html>