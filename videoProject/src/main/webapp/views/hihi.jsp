<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- <button id="btnload" name="btnmain" value="1" onclick="gfg_Run()">VIDEO1</button>
	<button id="btnload1" name="btnmain" value="2" onclick="gfg_Run()">VIDEO2</button>
	<button id="btnload2" name="btnmain" value="3" onclick="gfg_Run()">VIDEO3</button>
	<button id="btnload" name="btnmain" value="4" onclick="gfg_Run()">VIDEO4</button>
	<button id="btnload" name="btnmain" value="5" onclick="gfg_Run()">VIDEO5</button> -->
	<input type="text" id="id2">
	<c:forEach var="item" items="${video}" varStatus="hihi">
		<button id="btnload${hihi.count}" name="btnmain" value="${hihi.count}" onclick="gfg_Run(${hihi.count})">${hihi.count}</button>
	</c:forEach>

<script>
    var inputF1 = document.getElementById("btnload");
    var inputF2 = document.getElementById("id2");

    function gfg_Run(values) {
        // Selecting the input element and get its value 
        let inputVal = "";
			
        inputVal = document.getElementById("btnload"+values).value;
        console.log(inputVal);
        inputF2.value = inputVal;
    }
</script>
</body>
</html>