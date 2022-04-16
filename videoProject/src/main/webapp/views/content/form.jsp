<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Font-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content/form/form/css/roboto-font.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content/form/form/fonts/font-awesome-5/css/fontawesome-all.min.css">
    <!-- Main Style Css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/content/form/css/style.css" />
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
    <c:url var="url" value="/user"/>
	<div class="form-v5-content">
	<c:if test="${not empty form}">
		<script>
			if("${check}" == "1"){
				alert("${message}");
			}else if("${check}" == "0"){
				alert("${message}");
			}
        	
		</script>
        <form class="form-detail" action="${url}/index-user" method="post">
        
            <h2>Register Account Form</h2>
            <div class="form-row">
                <label for="full-name">ID</label>
                <input type="text" value="${form.id}" name="id" class="input-text" placeholder="Your ID" >

            </div>
            <div class="form-row">
                <label for="full-name">Full Name</label>
                <input type="text" value="${form.fullname}" name="fullname" class="input-text" placeholder="Your Name" >

            </div>
            <div class="form-row">
                <label for="your-email">Your Email</label>
                <input type="text" value="${form.email}" name="email" class="input-text" placeholder="Your Email"  pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}">

            </div>
            <div class="form-row">
                <label for="password">Password</label>
                <input type="password" value="${form.password}" name="password" class="input-text" placeholder="Your Password" >

            </div>
            <div class="form-row">
                <label for="full-name">Role</label>
                <span for="html" style="margin-right: 100px;"> <input type="radio"  ${form.admin?'':'checked'} name="admin" value="false">User</span>
                <span for="html"></span><input type="radio" ${form.admin?'checked':''} name="admin" value="true">Admin</label>
            </div>
            <div class="form-row-last">
                <button class="button-17" formaction="${url}/create" >Create</button>
                <button class="button-17" formaction="${url}/update" >Update</button>
                <button class="button-17" formaction="${url}/delete" >Delete</button>
                <button class="button-17" formaction="${url}/index-user" >Reset</button>
                
            </div>
        </form>
        
        </c:if>
    </div>
    