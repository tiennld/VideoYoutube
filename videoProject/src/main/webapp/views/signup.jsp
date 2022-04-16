<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/signup/signup.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<c:url var="url" value="/account"/>
	
	<div class="main">
		
        <input type="checkbox" id="chk" aria-hidden="true">

        <div class="signup">

            <form action="${url}/sign-up" method="post">
                <label for="chk" aria-hidden="true">Sign up</label>
                <input type="text"  name="id" placeholder="User id" required="">
                <input type="text"  name="fullname" placeholder="User name" required="">
                <input type="email"  name="email" placeholder="Email" required="">
                <input type="password"  name="password"  placeholder="Password" required="">
                <div class="forRadio">
                    <p for="full-name">Role:</p><br>
                    <span for="html" style="margin-right: 50px;"> <input type="radio"   name="admin" value="false">User</span>
                    <span for="html"></span><input type="radio"  name="admin" value="true">Admin</label>
                </div>
                <button>Sign up</button>
            </form>
        
        </div>
        

        <div class="login">
        	<script>
			if("${check}" == "1"){
				alert("${message}");
			}else if("${check}" == "0"){
				alert("${message}");
			}
        	
			</script>
            <form action="${url}/sign-in" method="post">
                <label for="chk" aria-hidden="true">Login</label>
                <input type="text" name="id" placeholder="Id" required="">
                <input type="password" name="password" placeholder="Password" required="">
                <button>Login</button>
            </form>
            
        </div>
    </div>
</body>
</html>