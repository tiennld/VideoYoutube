<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	body{
		width:1800px;
		
	}
	.left{
		float:left;
		width:670px;
		margin-top:15px;
		margin-left:300px;
		
		background:transparent;
	}
	
	.right{
		float:right;
		width:700px;
		margin-top:15px;
		margin-right:60px;
		overflow-x:hidden;
		background:transparent;
		height:650px;
	}
</style>
</head>
<body style="background: #fcfcfc;">
	<div class="left">
		<jsp:include page="form.jsp"></jsp:include>
	</div>
	<div class="right">
		<jsp:include page="table.jsp"></jsp:include>
	</div>
	
</body>
</html>