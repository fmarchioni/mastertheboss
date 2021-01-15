<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
<html>
<body>
 <h2>Edit Customer</h2>

<div class="divTable blueTable">

    <form method="POST" action="">
        <div class="divTableRow">
            <div class="divTableCell">Name:</div>
            <div class="divTableCell"><input type="text" name="name" id="name" value="${customer.name}" ></div>
        </div>
        <div class="divTableRow">
            <div class="divTableCell">Address:</div>
            <div class="divTableCell"><input type="text" name="address" id="address"  value="${customer.address}" ></div>
        </div>
        <div class="divTableRow">
            <div class="divTableCell">Email:</div>
            <div class="divTableCell"><input type="text" name="email" id="email"  value="${customer.email}" ></div>
        </div>
        <input type="submit" class="myButton" value="Save"/>
    </form>

 </div>

</body>
</html>

