<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
 <head></head>
 <body>
  <h2>Customers list</h2>

  <div>

    <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <td>Name</td>
              <td>Address</td>
              <td>Email</td>
              <td>Edit</td>
              <td>Delete</td>
            </tr>
          </thead>
          <tbody>

           	<tr th:each="customer : ${customers}">
           	    <td th:text="${customer.name}" />
           	    <td th:text="${customer.address}" />
           	    <td th:text="${customer.email}" />
           	    <td><a href="${pageContext.request.contextPath}/mvc/customers/edit/${customer.id}">Edit</a></td>
                <td><a href="${pageContext.request.contextPath}/mvc/customers/delete/${customer.id}">Delete</a></td>

           	</tr>
          </tbody>
        </table>


   <br>
   <form method="GET" action="${pageContext.request.contextPath}/mvc/customers/new">
    <button class="myButton" type="submit">Add new</button>
   </form>
  </div>
 </body>
</html>


