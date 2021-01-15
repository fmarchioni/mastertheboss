<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
<html>
 <head></head>
 <body>
  <h2>Customers list</h2>

  <div>
   <table class="blueTable">
   <thead>
   <tr>
   <th>Name</th>
   <th>Address</th>
   <th>Email</th>
   <th>Edit</th>
   <th>Delete</th>
   </tr>
   </thead>
    <c:forEach var="customer" items="${customers}">
     <tr>

      <td >${customer.name}</a></td>
      <td>${customer.address}</td>
      <td>${customer.email}</td>
      <td><a href="${pageContext.request.contextPath}/mvc/customers/edit/${customer.id}">Edit</a></td>
      <td><a href="${pageContext.request.contextPath}/mvc/customers/delete/${customer.id}">Delete</a></td>
     </tr>
    </c:forEach>
    <c:if test="${empty customers}">
        No Customers in the database.
    </c:if>
   </table>
   <br>
   <form method="GET" action="${pageContext.request.contextPath}/mvc/customers/new">
    <button class="myButton" type="submit">Add new</button>
   </form>
  </div>
 </body>
</html>


