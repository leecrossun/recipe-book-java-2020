<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<form name="form"
				action="<c:url value='/refrigerator/addIngredient' />">
			  <table id="table" border="1">
                    <tr>
                       <th width="300px">재료명</th>
						<th width="100px">양</th>
						<th width="100px">단위</th>
						
                    </tr>
                </table>
			
			</div>
			</form>
				<div>
                ingredientName :<input type="text" name="fIngredientName" id="fIngredientName">
                <input type=button class=searchBtn value='검색' onClick=openWin();>
                amount :<input placeholder="ex)300" class="form" type="number" id="fAmount"name="fAmount" style="width:60px; height:20px;">
                unit :<input placeholder="ex)g" class="form" type="text" name="fUnit" id="fUnit" style="width:60px; height:20px;">
               
                <button onclick="addHtmlTableRow();">Add</button>
                <!-- <button onclick="editHtmlTbleSelectedRow();">Edit</button> -->
                <button onclick="removeSelectedRow();">Remove</button>
            </div>
			<input type="submit" class="btn" value="재료 선택 완료">
			<script>
            
            var rIndex,
                table = document.getElementById("table");
            // check the empty input
            function checkEmptyInput()
            {
                var isEmpty = false,
               		fIngredientName = document.getElementById("fIngredientName").value,
               		fAmount = document.getElementById("fAmount").value,
               		fUnit = document.getElementById("fUnit").value;
            
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
           
                    fIngredientName = document.getElementById("fIngredientName").value,
                    fAmount = document.getElementById("fAmount").value,
                    fUnit = document.getElementById("fUnit").value;
            		
                	newRow.onmouseover = function() {
            			table.clickedRowIndex = this.rowIndex;
            		}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
            		
                
                cell1.innerHTML = "<input class=form type=text placeholder=검색버튼클릭 id=selectName name=ingredientName value='"+fIngredientName+"' style=width:200px height:20px;>";
                cell2.innerHTML =  "<input placeholder=ex)300 class=form type=text name=amount value='"+fAmount+"' style=width:60px; height:20px;> ";
                cell3.innerHTML =  "<input placeholder=ex)g class=form type=text name=unit value='"+fUnit+"' style=width:60px; height:20px;> ";
                
               
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
                fUnit = document.getElementById("fUnit").value;
               
               if(!checkEmptyInput()){
                table.rows[rIndex].cells[0].innerHTML = fIngredientName;
                table.rows[rIndex].cells[1].innerHTML = fAmount;
                table.rows[rIndex].cells[2].innerHTML = fUnit;
           
              }
            }
            
            function removeSelectedRow()
            {
                table.deleteRow(rIndex);
                // clear input text
                document.getElementById("fIngredientName").value = "";
                document.getElementById("fAmount").value = "";
                document.getElementById("fUnit").value = "";
               
            }
        </script>
</body>
</html>