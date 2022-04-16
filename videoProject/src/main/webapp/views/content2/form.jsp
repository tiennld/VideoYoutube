<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Font-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content2/form/form/css/roboto-font.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content2/form/form/fonts/font-awesome-5/css/fontawesome-all.min.css">
    <!-- Main Style Css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/content2/form/css/style.css" />
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
    <c:url var="url" value="/video"/>
	<div class="form-v5-content">
	<c:if test="${not empty form}">
		<script>
			if("${check}" == "1"){
				alert("${message}");
			}else if("${check}" == "0"){
				alert("${message}");
			}
        	
		</script>
        <form class="form-detail" action="${url}/index-video" method="get">
        
            <h2>Register Account Form</h2>
            <div class="form-row">
                <label for="full-name">ID</label>
                <input type="text" value="${form.id}" name="id" class="input-text" placeholder="ID" >

            </div>
            <div class="form-row">
                <label for="full-name">Title</label>
                <input type="text" value="${form.title}" name="title" class="input-text" placeholder="Title" >

            </div>
            <div class="form-row">
                <label for="your-email">Poster</label>
                <input type="text" value="${form.poster}" name="poster" class="input-text" placeholder="Poster" >

            </div>
            <div class="form-row">
                <label for="your-email">Views</label>
                <input type="text" value="${form.views}" name="views" class="input-text" placeholder="Poster" >

            </div>
            <div class="form-row">
                <label for="password">Description</label>
                <input type="password" value="${form.description}" name="description" class="input-text" placeholder="Description" >

            </div>
            <div class="form-row">
                <label for="full-name">Status</label>
                <span for="html" style="margin-right: 100px;"> <input type="radio"  ${form.active?'':'checked'} name="admin" value="false">In-active</span>
                <span for="html"></span><input type="radio" ${form.active?'checked':''} name="admin" value="true">Active</label>
            </div>
            <div class="form-row-last">
                <button class="button-17" formaction="${url}/create" >Create</button>
                <button class="button-17" formaction="${url}/update" >Update</button>
                <button class="button-17" formaction="${url}/delete" >Delete</button>
                <button class="button-17" formaction="${url}/index-video" >Reset</button>
                
            </div>
        </form>
        
        </c:if>
    </div>
    