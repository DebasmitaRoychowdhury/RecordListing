<!DOCTYPE html>
<html>
<head>
<script>
	var arr = [];
	var oldtrnode;

	function saveInfo(actionObj) {
		var request = new XMLHttpRequest();
		var action = actionObj.value;
		var name = document.getElementById("name").value;
		var id = document.getElementById("id").value;
		var salary = document.getElementById("salary").value;
		var age = document.getElementById("age").value;
		var url = "servlet1?name=" + name + "&id=" + id + "&salary=" + salary
				+ "&age=" + age + "&action=" + action;
		//alert(url);
		//document.getElementById("form1").reset();
		request.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var trnode = document.createElement("tr");
				trnode.innerHTML = this.responseText;
				
				if (action == "Insert")
					document.getElementById("demo").appendChild(trnode);
				else if (action == "Update")
					document.getElementById("demo").replaceChild(trnode,oldtrnode);
				else if (action == "Delete")
					document.getElementById("demo").removeChild(oldtrnode);
				else
					alert("Please select an action");

			}
		};
		document.getElementById("form1").reset();
		request.open("GET", url, true);
		//document.getElementById("form1").reset();
		request.send();

	}

	function getRow(n) {
		if (n.checked == false) {
			document.getElementById("form1").reset();
			return;
		}
		oldtrnode = n.parentNode.parentNode;
		var cols = oldtrnode.getElementsByTagName("td");
		//var element=document.getElementsByTagName("input");
		var i = 1, j = 0;
		while (i < cols.length) {
			var val = cols[i].childNodes[0].nodeValue;

			if (val != null) {
				arr[j] = val;

			}
			i++;
			j++;
		}
		document.getElementById("name").value = arr[0];
		document.getElementById("id").value = arr[1];
		document.getElementById("salary").value = arr[2];
		document.getElementById("age").value = arr[3];
	}
	function showInfo() {
		//alert("on load started");
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//alert("on load completed");
				var ajaxDisplay = document.getElementById("demo");
				//var trnode = document.createElement("table");
				//trnode.innerHTML = this.responseText;
				
				//document.getElementById("demo").appendChild(trnode);
				ajaxDisplay.innerHTML = this.responseText;
			}
		};
		xhr.open("GET", "servlet1?action=load", true);
		xhr.send();
	}
</script>
</head>
<body onload="showInfo()">
	<form id="form1">

		Name:<input type="text" name="name" id="name"><br /> <br />
		Id:<input type="text" name="id" id="id"><br /> <br />
		Salary:<input type="text" name="salary" id="salary"><br /> <br />
		Age:<input type="text" name="age" id="age"> <br /> <br /> 
		<input type="button" value="Insert" onclick="saveInfo(this)"> 
		<input type="button" value="Update" onclick="saveInfo(this)"> 
		<input type="button" value="Delete" onclick="saveInfo(this)">
		

	</form>
	<hr>
	<div>
		<table border='1'>
			<thead>
				<tr>
					<td>Select Row</td>
					<td>Name</td>
					<td>Id</td>
					<td>Salary</td>
					<td>Age</td>
				</tr>
			</thead>
			<tbody id="demo">

			</tbody>
		</table>
	</div>
</body>
</html>