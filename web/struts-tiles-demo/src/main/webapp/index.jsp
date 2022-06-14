<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Struts 2 & Tiles Example Application</title>
</head>
<body>
<h2>Struts 2 & Tiles Example Application</h2>

<s:form action="login.action" method="post">
    <s:textfield name="username" label="Username" size="20" />
    <s:password name="password" label="Password" size="20" />
    <s:submit method="execute" label="Login" align="center" />
</s:form>
</body>
</html>