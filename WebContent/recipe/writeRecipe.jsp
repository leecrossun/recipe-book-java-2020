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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
tr, td {
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
<script language="javascript">
	var oTbl;
	//Row 추가
	function insRow() {
		oTbl = document.getElementById("addTable");
		var oRow = oTbl.insertRow();
		oRow.onmouseover = function() {
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
		if(frm.difficulty.value == ""){
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
	
	 
	$(document).ready(function(){
	    $('#selectDiff').change(function(){
	        var selectedText = $("#selectDiff option:selected").val();
	        $("#difficulty").val(selectedText);
	    })
	});
	
</script>
</head>

<body>
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
		<p class="menu">${sessionScope.userId }님</p>
		<p class="menu">🛒Refrigerator</p>
		<p class="menu">⚙ Settings</p>
	</div>
	<div class="container">
		<p class="mainTitle">💜 레시피 등록 💜</p>
	</div>
	<div class="container">
		<br>
		<p class="mainTitle">🍧 나만의 레시피를 등록해보세요! 🍧</p>
		<br>
		<div class="sub-container" style="margin: 0px auto;">
			<form name="form" method="POST" action="<c:url value='/recipe/create' />">
				<table>
					<tr>
						<td width="100px">레시피 이름</td>
						<td width="700px"><input name="recipeName" type="text" class="form"
							style="width: 90%;" placeholder="레시피명을 적어주세요"></td>
					</tr>
					<tr>
						<td>한줄 설명</td>
						<td><input name="summary" type="text" class="form" style="width: 90%;"
							placeholder="레시피를 한줄로 설명해주세요"></td>
					</tr>
					<tr>
						<td>음식 사진</td>
						<td><input name="image" type="file" class="form"></td>
					</tr>
					<tr>
						<td>필요한 재료</td>
						<td><input name="ingList" type="text" class="form" placeholder="재료명 입력"><a
							class="btn">검색</a><a class="btn">등록</a></td>
					</tr>
					<tr>
						<td>음식 나라명</td>
						<td><input name="nation" type="text" class="form" placeholder="나라 입력"></td>
					</tr>
					
					<tr>
						<td>요리난이도</td>
						<td>
						<input name="difficulty" id="difficulty" type="text" class="form-control"> 
						<select id="selectDiff">
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
							<table id="addTable"  class="stepTable" style="border:none;"></table>
							<input name="addButton" type="button" class="btn"
							style="cursor: hand; float:right;" onClick="insRow()" value="추가">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="button-box">
								<input type="button" class="btn" onClick="frmCheck()"
									value="레시피등록">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
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