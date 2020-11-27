<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Add Edit Remove HTML Table Row</title>
        <meta charset="windows-1252">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <style>
            
            .container{overflow: hidden}
            .tab{float: left;}
            .tab-2{margin-left: 50px}
            .tab-2 input{display: block;margin-bottom: 10px}
            tr{transition:all .25s ease-in-out}
            tr:hover{background-color:#EEE;cursor: pointer}
            
        </style>
        <script type="text/javascript">
	
		function openWin(){
			window.open("/RecipeBook/ingredient/find.jsp", "재료검색", "width=500, height=600");
		}
		function setChildValue(name) {
			document.getElementById("fIngredientName").value = name;
		}
		</script>
    </head>
    <body>
        
        <div class="container">
            <div class="tab tab-1">
                <table id="table" border="1">
                    <tr>
                       <th width="300px">재료명</th>
						<th width="100px">양</th>
						<th width="100px">단위</th>
						<th width="300px">유통</th>
						
                    </tr>
                    <tr>
                      	<td><input class=form type=text placeholder=검색버튼클릭 id=selectName name=ingredientName value=재료1 ></td>
                     	<td><input placeholder=ex)300 class=form type=text name=amount value=10 ></td>
                     	<td><input placeholder=ex)g class=form type=text name=unit value=kg > </td>
                     	<td><input class=form type=date id=currentDate name=expiredDate value=2020-10-17 > </td>
                     	
                    </tr>
                    <tr>
                        <td><input class=form type=text placeholder=검색버튼클릭 id=selectName name=ingredientName value=재료2 ></td>
                     	<td><input placeholder=ex)300 class=form type=text name=amount value=100 ></td>
                     	<td><input placeholder=ex)g class=form type=text name=unit value=g ></td>
                     	<td><input class=form type=date id=currentDate name=expiredDate value=2020-10-18 ></td>
                     	
                    </tr>
                    <tr>
                        <td><input class=form type=text placeholder=검색버튼클릭 id=selectName name=ingredientName value=재료3 ></td>
                     	<td><input placeholder=ex)300 class=form type=text name=amount value=5 ></td>
                     	<td><input placeholder=ex)g class=form type=text name=unit value=개></td>
                     	<td><input class=form type=date id=currentDate name=expiredDate value=2020-10-18 ></td>
                     	
                    </tr>
                </table>
            </div>
            <div class="tab tab-2">
                ingredientName :<input type="text" name="fIngredientName" id="fIngredientName">
                <input type=button class=searchBtn value='검색' onClick=openWin();>
                amount :<input placeholder="ex)300" class="form" type="number" id="fAmount"name="fAmount" style="width:60px; height:20px;">
                unit :<input placeholder="ex)g" class="form" type="text" name="fUnit" id="fUnit" style="width:60px; height:20px;">
                expire date:<input class="form" type="date" id="fExpireDate" name="fExpireDate" style="width:200px; height:20px;">
                <button onclick="addHtmlTableRow();">Add</button>
                <button onclick="editHtmlTbleSelectedRow();">Edit</button>
                <button onclick="removeSelectedRow();">Remove</button>
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
            		
                //cell1.innerHTML = fIngredientName;
                //cell2.innerHTML = fAmount;
                //cell3.innerHTML = fUnit;
                //cell4.innerHTML = fExpireDate;
                
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
                      //document.getElementById("fIngredientName").value = this.cells[0].value;
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