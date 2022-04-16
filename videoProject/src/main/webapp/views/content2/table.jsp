<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content2/table/style.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/content2/table/button.css">
    <!--===============================================================================================-->
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<c:url var="url" value="/video"/>

<!-- Bảng -->
<c:if test="${not empty items }">    
<table class="table">
        <thead class="table__thead">
          <tr class="table__head">
            <th class="table__th">ID</th>
            <th class="table__th">Title</th>
            <th class="table__th">Views count</th>
            <th class="table__th">Status</th>
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
              <span class="table__value">${item.title}</span>
            </td>
            <td class="table__td">
              <span class="table__value">${item.views}</span>
            </td>
            <td class="table__td">
              <span class="table__value">${item.active?'Active':'In-active'}</span>
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
	   <form class="form-detail" action="${url}/index" method="get">
		   	<c:forEach var="num" items="${number}">
					 <button type="submit" class="button-12" value="${num}" name="numberPage" formaction="${url}/video-number" >${num}</button>
			</c:forEach>
	   </form>
   </div>