<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<br/>
<s:url  namespace="/" action="logout.action" value="To exit ->" />


<s:url  action="logout.action" var="urlTag" />

<s:a href="%{urlTag}">Logout</s:a>

<s:a errorText="Sorry your request had an error."
    href="%{testUrlId}">

</s:a>
</div>