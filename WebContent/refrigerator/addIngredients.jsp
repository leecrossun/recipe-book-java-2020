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

		td .btn {
			font-size: 15px;
		}

		td>input {
			width: 100px;
			border: 1.5px solid rgb(255, 67, 192);
			box-shadow: 3px 3px rgb(115, 28, 214);
		}
	</style>
	
	<script type="text/javascript">
	function openWin(){
		window.open("/RecipeBook/ingredient/find.jsp", "재료검색", "width=500, height=600");
		
	}
	function setChildValue(name) {
		document.getElementById("selectName").value = name;
	}
	
	document.getElementById("currentDate").value = new Date().toISOString().substring(0, 10);;
	
	var oTbl;
	//Row 추가
	function insRow() {
		oTbl = document.getElementById("addTableBody");
		var oRow = oTbl.insertRow();
		oRow.onmouseover = function() {
			oTbl.clickedRowIndex = this.rowIndex
		}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
		
		var oCell1 = oRow.insertCell(0);
		var oCell2 = oRow.insertCell(1);
		var oCell3 = oRow.insertCell(2);
		var oCell4 = oRow.insertCell(3);
		var oCell5 = oRow.insertCell(4);
	
		
		//삽입될 Form Tag type="date" id="currentDate"
		oCell1.innerHTML = "<input class=form type=text placeholder=검색버튼 클릭 id=selectName name=ingredientName style=width:80px height:20px;> <input type=button class=searchBtn value='검색' onClick=javascript:openWin()>";
		oCell2.innerHTML =  "<input placeholder=ex)300 class=form type=text name=amount style=width:60px; height:20px;> ";
		oCell3.innerHTML =  "<input placeholder=ex)g class=form type=text name=unit style=width:60px; height:20px;> ";
		oCell4.innerHTML =  "<input class=form type=date id=currentDate name=expiredDate style=width:60px; height:20px;> ";
		oCell5.innerHTML =  "<input type=button value='삭제' class='btn' onClick='removeRow()' style='cursor:hand'>";
	}
	//Row 삭제
	function removeRow() {
		oTbl.deleteRow(oTbl.clickedRowIndex);
	}
	
	function frmCheck() {
		var frm = document.form;
		
		if (frm.ingredientName.value =="") {
			alert("재료명을 입력하십시오.");
			frm.ingredientName.focus();
			return false;
		}
		
		if (frm.amount.value =="") {
			alert("양을 입력하십시오.");
			frm.ingredientName.focus();
			return false;
		}
		
		if (frm.unit.value =="") {
			alert("단위를 입력하십시오.");
			frm.ingredientName.focus();
			return false;
		}
		
		if (frm.expiredDate.value =="") {
			alert("유통기한을 입력하십시오.");
			frm.ingredientName.focus();
			return false;
		}
		
		frm.submit();
	}
	</script>
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
		<p class="menu">${userId} 님</p>
		<p class="menu">🛒Refrigerator</p>
		<p class="menu">⚙ Settings</p>
	</div>
	<!-- TItle Area -->
	<div class="container">
		<p class="mainTitle">💜 Add Ingredients 💜</p>
		<br>
		<p class="mainTitle">Please Select Ingredients to Add</p>
		<br>
	</div>
	<div class="container" style="border: none;">
		<div class="table" style="width: 800px;">
			<p class="title">정보 입력</p>
			<form name="form"
				action="<c:url value='/refrigerator/addIngredient' />">
			<table name="addTable">
				<thead>
					<th>재료명</th><th>양</th><th>단위</th><th>유통기한</th><th>해당행삭제</th><th>추가</th>
				</thead>
				
				<tbody id="addTableBody"></tbody>
			</table>
			<input name="addButton" type="button" class="btn" style="cursor: hand; float:right;" onClick="insRow()" value="추가">
			<div style="border: none;">
								<a class="btn" onClick="frmCheck()"> ADD </a>
			</div>
			</form>
			<!-- <table>
				<p class="title">정보 입력</p>
				<th width="100px">재료명</th>
				<th>양</th>
				<th>단위</th>
				<th>유통기한</th>
				<th>추가</th>
				<tr>
					<td><input type="text" placeholder="검색버튼 클릭" id="selectName"><button class="searchBtn" onClick="javascript:openWin()">검색</button></td>
					<td><input type="text" placeholder="ex) 300"></td>
					<td><input type="text" placeholder="ex) g"></td>
					<td><input type="date" id="currentDate"></td>
					<td>
						<div style="border: none;">
							<a name="addButton" class="btn"> ADD </a>
						</div>
					</td>
				</tr>
			</table>  -->
		</div>
	</div>
</body>

</html>

