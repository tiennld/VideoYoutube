<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content/table/style.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content/table/button.css">
    <!--===============================================================================================-->
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<c:url var="url" value="/user"/>

<!-- Bảng -->
<c:if test="${not empty items }">    
<table class="table">
        <thead class="table__thead">
          <tr class="table__head">
            <th class="table__th">ID</th>
            <th class="table__th">Password</th>
            <th class="table__th">Full name</th>
            <th class="table__th">Email</th>
            <th class="table__th">Role</th>
            <th class="table__th">Edit</th>
          </tr>
        </thead>
        <tbody class="table__tbody" >
     
          <c:forEach var="item" items="${items }">
			 <tr class="table__tr">
            <td class="table__td">
              <span class="table__value">${item.id}</span>
            </td>
            <td class="table__td">
              <span class="table__value">${item.password}</span>
            </td>
            <td class="table__td">
              <span class="table__value">${item.fullname}</span>
            </td>
            <td class="table__td">
              <span class="table__value">${item.email}</span>
            </td>
            <td class="table__td">
              <span class="table__value">${item.admin?'Admin':'User'}</span>
            </td>
            <td class="table__td">
              <span class="table__value"><a href="${url}/edit/${item.id}">Edit</a></span>
            </td>
          </tr>
		  </c:forEach>
        </tbody>
       
      </table>
       
   </c:if>   
   <div class="moveButton" style="position: absolute;left: 1040px;top: 430px;width:670px;">
	   <form class="form-detail" action="${url}/page" method="post">
		   	<c:forEach var="num" items="${number}">
					 <button type="submit" class="button-12" value="${num}" name="numberPage" formaction="${url}/page" >${num}</button>
			</c:forEach>
	   </form>
   </div>