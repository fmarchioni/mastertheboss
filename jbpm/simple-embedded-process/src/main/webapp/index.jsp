<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Simple Embedded Process</title>
</head>
<body>
<p>Start a process</p>
<p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %></p>
<form method="post" action="process">
<input type="submit" value="Start a process">
</form>
</body>
</html>