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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<title>레시피 등록</title>
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
			width: 90%;
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

		/* 조리과정 */
		.stepTable td {
			border: 1px solid white;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#selectDiff').change(function () {
				var selectedText = $("#selectDiff option:selected").val();
				$("#difficulty").val(selectedText);
			})
		});

		function openWin() {
			window.open("/RecipeBook/ingredient/find.jsp", "재료검색", "width=500, height=600");
		}

		function setChildValue(name) {
			document.getElementById("fIngredientName").value = name;
		}

		var oTbl;

		function insRow() {
			oTbl = document.getElementById("addTable");
			var oRow = oTbl.insertRow();
			oRow.onmouseover = function () {
				oTbl.clickedRowIndex = this.rowIndex
			}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
			var oCell = oRow.insertCell();

			//삽입될 Form Tag
			var frmTag = "<input class=form type=text name=stepList style=width:350px; height:20px;> ";
			frmTag += "<input type=button value='삭제' class='btn' onClick='removeRow()' style='cursor:hand'>";
			oCell.innerHTML = frmTag;
		}
		//Row 삭제
		function removeRow() {
			oTbl.deleteRow(oTbl.clickedRowIndex);
		}
		
		
	</script>
</head>

<body>
	<!-- Navigation Bar -->
	 <%@include file="../static/nav.jsp"%>

	<!-- Write Area -->
	<div class="container">
		<p class="mainTitle">💜 레시피 등록 💜</p>
	</div>
	<div class="container">
		<br>
		<p class="mainTitle">🍧 나만의 레시피를 등록해보세요! 🍧</p>
		<br>
		<div class="sub-container" style="margin: 0px auto;">
			<form name="form" method="POST" enctype="multipart/form-data" action="<c:url value='/recipe/create' />">
				<table>
					<tr>
						<td width="100px">레시피 이름</td>
						<td width="700px"><input name="recipeName" type="text" class="form" style="width: 90%;"
								placeholder="레시피명을 적어주세요"></td>
					</tr>
					<tr>
						<td>한줄 설명</td>
						<td><input name="summary" type="text" class="form" style="width: 90%;"
								placeholder="레시피를 한줄로 설명해주세요"></td>
					</tr>
					<tr>
						<td>음식 사진</td>
						<td><!-- <input name="image" type="file" class="form"> -->
							<input type="file" name="image">
						</td>
					</tr>
					<tr>
						<td>필요한 재료</td>
						<!--  <td><input name="ingList" type="text" class="form" placeholder="재료명 입력"><a class="btn">검색</a><a
								class="btn">등록</a></td>-->
						<td>
							<table id="table" border="1">
								<tr>
									<th width="300px">재료명</th>
									<th width="100px">양</th>
									<th width="100px">단위</th>

								</tr>
							</table>

							<div>
								ingredientName :<input type="text" name="fIngredientName" id="fIngredientName" placeholder="검색버튼 클릭" disabled>
								<input type=button class=searchBtn value='검색' onClick=openWin();>
								amount :<input placeholder="ex)300" class="form" type="number" id="fAmount"
									name="fAmount" style="width:60px; height:20px;">
								unit :<input placeholder="ex)g" class="form" type="text" name="fUnit" id="fUnit"
									style="width:60px; height:20px;">

								<input type="button" onclick="addHtmlTableRow();" value="ADD">
								<!-- <button onclick="editHtmlTbleSelectedRow();">Edit</button> -->
								<input type="button" onclick="removeSelectedRow();" value="REMOVE">
							</div>
						</td>
					</tr>
					<tr>
						<td>음식 나라명</td>
						<td><input name="nation" type="text" class="form" placeholder="나라 입력"></td>
					</tr>

					<tr>
						<td>요리난이도</td>
						<td>
							<select id="selectDiff" name="difficulty">
								<option value="">직접 입력</option>
								<option value="상">상</option>
								<option value="중상">중상</option>
								<option value="중">중</option>
								<option value="중하">중하</option>
								<option value="하">하</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>조리 과정</td>
						<td style="border:none;">
							<table id="addTable" class="stepTable" style="border:none;"></table>
							<input name="addButton" type="button" class="btn" style="cursor: hand; float:right;"
								onClick="insRow()" value="추가">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="button-box">
								<!-- <input type="button" class="btn" onClick="frmCheck()" value="레시피등록"> -->
								<input type="submit" class="btn" onClick="frmCheck()" value="레시피등록">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
	<script type="text/javascript">
		function frmCheck() {
			var frm = document.form;

			if (frm.recipeName.value == "") {
				alert("레시피명을 입력하십시오.");
				frm.recipeName.focus();
				return false;
			}
			if (frm.summary.value == "") {
				alert("summary를 입력하십시오.");
				frm.summary.focus();
				return false;
			}
			if (frm.nation.value == "") {
				alert("해당되는 나라를 입력하십시오.");
				frm.nation.focus();
				return false;
			}
			if (frm.difficulty.value == "") {

				alert("해당하는 난이도를 선택해주십시오");
				frm.difficulty.focus();
				return false;
			}
			for (var i = 0; i <= frm.elements.length - 1; i++) {
				if (frm.elements[i].name == "stepList") {
					if (!frm.elements[i].value) {
						alert("조리과정 텍스트박스에 값을 입력하세요!");
						frm.elements[i].focus();
						return false;
					}
				}
			}

			frm.submit();
		}
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
			} else if (fAmount === "") {
				alert("fAmount Connot Be Empty");
				isEmpty = true;
			} else if (fUnit === "") {
				alert("fUnit Connot Be Empty");
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
					cell2 = newRow.insertCell(1),
					cell3 = newRow.insertCell(2),

					fIngredientName = document.getElementById("fIngredientName").value,
					fAmount = document.getElementById("fAmount").value,
					fUnit = document.getElementById("fUnit").value;

				newRow.onmouseover = function () {
					table.clickedRowIndex = this.rowIndex;
				}; //clickedRowIndex - 클릭한 Row의 위치를 확인;


				cell1.innerHTML = "<input class=form type=text placeholder=검색버튼클릭 id=selectName name=ingName value='" +
					fIngredientName + "' style=width:200px height:20px;>";
				cell2.innerHTML = "<input placeholder=ex)300 class=form type=number name=amount value='" + fAmount +
					"' style=width:60px; height:20px;> ";
				cell3.innerHTML = "<input placeholder=ex)g class=form type=text name=unit value='" + fUnit +
					"' style=width:60px; height:20px;> ";


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
					//document.getElementById("fAmount").value = this.cells[1].innerHTML;
					//document.getElementById("fUnit").value = this.cells[2].innerHTML;
					//document.getElementById("fExpireDate").value = this.cells[3].innerHTML;
				};
			}
		}
		selectedRowToInput();

		function editHtmlTbleSelectedRow() {
			var fIngredientName = document.getElementById("fIngredientName").value,
				fAmount = document.getElementById("fAmount").value,
				fUnit = document.getElementById("fUnit").value;

			if (!checkEmptyInput()) {
				table.rows[rIndex].cells[0].innerHTML = fIngredientName;
				table.rows[rIndex].cells[1].innerHTML = fAmount;
				table.rows[rIndex].cells[2].innerHTML = fUnit;

			}
		}

		function removeSelectedRow() {
			table.deleteRow(rIndex);
			// clear input text
			document.getElementById("fIngredientName").value = "";
			document.getElementById("fAmount").value = "";
			document.getElementById("fUnit").value = "";

		}
	</script>
	<!-- 
	<form>
		<div class="form-group">
			<label for="exampleFormControlTextarea1">만드는 방법</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
				placeholder="1. ... 2. ..."></textarea>
		</div>
	</form>

	<button type="button" class="mx-auto btn btn-outline-dark">레시피 등록</button> -->
</body>