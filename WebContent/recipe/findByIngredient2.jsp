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

	<style>
		.btn {
			text-align: center;
			width: 100px;
			height: 100px;
			margin: 3px;
			padding: 5px;
			border: 1px solid black;
			color: rgb(0, 0, 0);
			font-size: 10pt;
		}

		.btn:hover {
			background-color: rgb(177, 53, 248);
			color: black;
		}

		#columns {
			display: grid;
			grid-gap: 10px;
			grid-template-rows: repeat(2, 1fr);
			grid-template-columns: repeat(5, 1fr);
			padding: 10px;
		}

		#columns .figure {
			display: inline-block;
			border: 1px solid rgba(0, 0, 0, 0.2);
			margin: 0;
			margin-bottom: 15px;
			padding: 10px;
			box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
		}

		#columns .figure img {
			width: 100%;
		}

		#columns .figure figcaption {
			border-top: 1px solid rgba(0, 0, 0, 0.2);
			padding: 10px;
			margin-top: 11px;
		}
	</style>
</head>
<script type="text/javascript">

		function openWin() {
			window.open("/RecipeBook/ingredient/find.jsp", "재료검색", "width=500, height=600");
		}

		function setChildValue(name) {
			document.getElementById("fIngredientName").value = name;
		}
	</script>
<body>
	<!-- Navigation Bar -->
	<%@include file="../static/nav.jsp"%>

	<!-- TitleArea -->
	<div class="container">
		<div class="container" style=" width:100%;">
			<div class="table" style="width:100%; border: none; margin: 0px auto;">
				<p class="title">추가할 재료 입력</p>
				<form name="form"
					action="<c:url value='/recipe/findByIng' />">
				  <table id="table" border="1">
						<tr>
						   <th width="300px">재료명</th>
						</tr>
					</table>
					<input type="submit" class="btn" value="재료 선택 완료">
				</form>
					<div>
					ingredientName :<input type="text" name="fIngredientName" id="fIngredientName">
					<input type=button class=searchBtn value='검색' onClick=openWin();>
					<button onclick="addHtmlTableRow();">Add</button>
					<button onclick="editHtmlTbleSelectedRow();">Edit</button>
					<button onclick="removeSelectedRow();">Remove</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<p class="mainTitle">💜 검색 결과 💜</p>
	</div>
	<div id="columns">
		<c:forEach var="recipe" items="${SearchedRcps }">
		<div class="figure">
			<img src="${recipe.image }" alt="${recipe.recipeName }">
			<figcaption>${recipe.recipeName }</figcaption>
			<p class="summary">${recipe.summary }</p>
			<hr>
			<div style="text-align: right;">
				<a class="btn" href="<c:url value='/recipe/view'>
											<c:param name='recipeId' value='${recipe.recipeId}'/>
											</c:url>">MORE</a>
				<br>
			</div>
		</div>
		</c:forEach>
		</div>

</body>

<script type="text/javascript">
		var rIndex,
			table = document.getElementById("table");
		// check the empty input
		function checkEmptyInput() {
			var isEmpty = false,
				fIngredientName = document.getElementById("fIngredientName").value,
				fAmount = document.getElementById("fAmount").value,
				fUnit = document.getElementById("fUnit").value;

			if (fIngredientName === "") {
				alert("fIngredientName Connot Be Empty");
				isEmpty = true;
			}
			return isEmpty;
		}

		// add Row
		function addHtmlTableRow() {
			// get the table by id
			// create a new row and cellsg
			// get value from input text
			// set the values into row cell's
			if (!checkEmptyInput()) {
				var newRow = table.insertRow(),

				cell1 = newRow.insertCell(0),

				fIngredientName = document.getElementById("fIngredientName").value,

				newRow.onmouseover = function () {
					table.clickedRowIndex = this.rowIndex;
				}; //clickedRowIndex - 클릭한 Row의 위치를 확인;


				cell1.innerHTML = "<input class=form type=text placeholder=검색버튼클릭 id=selectName name=ingName value='" +
					fIngredientName + "' style=width:200px height:20px;>";

				// call the function to set the event to the new row
				selectedRowToInput();
			}
		}
		//Row 삭제
		function removeRow() {
			table.deleteRow(table.clickedRowIndex);
		}

		// display selected row data into input text
		function selectedRowToInput() {

			for (var i = 1; i < table.rows.length; i++) {
				table.rows[i].onclick = function () {
					// get the seected row index
					rIndex = this.rowIndex;

					//document.getElementById("fIngredientName").value = this.cells[0].innerHTML;
				};
			}
		}
		selectedRowToInput();

		function editHtmlTbleSelectedRow() {
			var fIngredientName = document.getElementById("fIngredientName").value,

			if (!checkEmptyInput()) {
				table.rows[rIndex].cells[0].innerHTML = fIngredientName;
			}
		}

		function removeSelectedRow() {
			table.deleteRow(rIndex);
			// clear input text
			document.getElementById("fIngredientName").value = "";
		}
	</script>

</html>