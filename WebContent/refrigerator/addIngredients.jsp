<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />" />
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
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

		th {
			background-color: rgb(233, 233, 233);
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
			/* border: 1.5px solid rgb(255, 67, 192); */
			border: 1px solid black;
			box-shadow: 3px 3px rgb(115, 28, 214);
		}

	</style>
	
	<script type="text/javascript">
	function openWin(){
		window.open("/RecipeBook/ingredient/find.jsp", "재료검색", "width=500, height=600");
	}
	function setChildValue(name) {
		document.getElementById("fIngredientName").value = name;
	}
	document.getElementById("currentDate").value = new Date().toISOString().substring(0, 10);
	</script>
</head>

<body>
	<!-- Navigation Bar -->
 	<%@include file="../static/nav.jsp"%>

	<!-- TItle Area -->
	<div class="container">
		<p class="mainTitle">💜 Add Ingredients 💜</p>
		<br>
		<p class="mainTitle">Please Select Ingredients to Add</p>
		<br>
	</div>
	<div class="container" style=" width:100%;">
		<div class="table" style="width:100%; border: none; margin: 0px auto;">
			<p class="title">추가할 재료 입력</p>
			<form name="form"
				action="<c:url value='/refrigerator/addIngredient' />">
			  <table id="table" border="1">
                    <tr>
                       <th width="300px">재료명</th>
						<th width="100px">양</th>
						<th width="100px">단위</th>
						<th width="300px">유통</th>
                    </tr>
                </table>
			
			</div>
			</form>
				<div>
                ingredientName :<input type="text" name="fIngredientName" id="fIngredientName">
                <input type=button class=searchBtn value='검색' onClick=openWin();>
                amount :<input placeholder="ex)300" class="form" type="number" id="fAmount"name="fAmount" style="width:60px; height:20px;">
                unit :<input placeholder="ex)g" class="form" type="text" name="fUnit" id="fUnit" style="width:60px; height:20px;">
                expire date:<input class="form" type="date" id="fExpireDate" name="fExpireDate" style="width:200px; height:20px;">
                <button onclick="addHtmlTableRow();">Add</button>
                <button onclick="editHtmlTbleSelectedRow();">Edit</button>
                <button onclick="removeSelectedRow();">Remove</button>
            </div>
			<input type="submit" class="btn" value="재료 선택 완료">
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
	<script>
            
            var rIndex,
                table = document.getElementById("table");
            document.getElementById("fExpireDate").value = new Date().toISOString().substring(0, 10);
            // check the empty input
            function checkEmptyInput()
            {
                var isEmpty = false,
               		fIngredientName = document.getElementById("fIngredientName").value,
               		fAmount = document.getElementById("fAmount").value,
               		fUnit = document.getElementById("fUnit").value;
                	fExpireDate = document.getElementById("fExpireDate").value;
            
                if(fIngredientName === ""){
                    alert("fIngredientName Connot Be Empty");
                    isEmpty = true;
                }
                else if(fAmount === ""){
                    alert("fAmount Connot Be Empty");
                    isEmpty = true;
                }
                else if(fUnit === ""){
                    alert("fUnit Connot Be Empty");
                    isEmpty = true;
                }else if(fExpireDate === ""){
                    alert("fExpireDate Connot Be Empty");
                    isEmpty = true;
                }
                return isEmpty;
            }
            
            // add Row
            function addHtmlTableRow()
            {
                // get the table by id
                // create a new row and cells
                // get value from input text
                // set the values into row cell's
                if(!checkEmptyInput()){
                var newRow = table.insertRow(),
               
                    cell1 = newRow.insertCell(0),
                    cell2 = newRow.insertCell(1),
                    cell3 = newRow.insertCell(2),
                    cell4 = newRow.insertCell(3),
           
                    fIngredientName = document.getElementById("fIngredientName").value,
                    fAmount = document.getElementById("fAmount").value,
                    fUnit = document.getElementById("fUnit").value;
                	fExpireDate = document.getElementById("fExpireDate").value;
            
            		
                	newRow.onmouseover = function() {
            			table.clickedRowIndex = this.rowIndex;
            		}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
            		
                
                cell1.innerHTML = "<input class=form type=text placeholder=검색버튼클릭 id=selectName name=ingredientName value='"+fIngredientName+"' style=width:200px height:20px;>";
                cell2.innerHTML =  "<input placeholder=ex)300 class=form type=text name=amount value='"+fAmount+"' style=width:60px; height:20px;> ";
                cell3.innerHTML =  "<input placeholder=ex)g class=form type=text name=unit value='"+fUnit+"' style=width:60px; height:20px;> ";
                cell4.innerHTML =  "<input class=form type=date id=currentDate name=expiredDate value='"+fExpireDate+"' style=width:200px; height:20px;> ";
               
                // call the function to set the event to the new row
                selectedRowToInput();
            }
            }
          //Row 삭제
        	function removeRow() {
        		table.deleteRow(table.clickedRowIndex);
        	}
        	
            // display selected row data into input text
            function selectedRowToInput()
            {
                
                for(var i = 1; i < table.rows.length; i++)
                {
                    table.rows[i].onclick = function()
                    {
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
            
            function editHtmlTbleSelectedRow()
            {
                var fIngredientName = document.getElementById("fIngredientName").value,
                fAmount = document.getElementById("fAmount").value,
                fUnit = document.getElementById("fUnit").value,
                fExpireDate = document.getElementById("fExpireDate").value;
               if(!checkEmptyInput()){
                table.rows[rIndex].cells[0].innerHTML = fIngredientName;
                table.rows[rIndex].cells[1].innerHTML = fAmount;
                table.rows[rIndex].cells[2].innerHTML = fUnit;
                table.rows[rIndex].cells[3].innerHTML = fExpireDate;
              }
            }
            
            function removeSelectedRow()
            {
                table.deleteRow(rIndex);
                // clear input text
                document.getElementById("fIngredientName").value = "";
                document.getElementById("fAmount").value = "";
                document.getElementById("fUnit").value = "";
                document.getElementById("fExpireDate").value="";
            }
        </script>
</body>

</html>
