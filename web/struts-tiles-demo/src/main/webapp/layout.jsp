<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>

</head>
<body id="home">
<div id="header">
    <tiles:insertAttribute name="header" />
</div>
<div id="wrap">
    <div id="content">
        <tiles:insertAttribute name="body" />
    </div>
    <div id="sidebar">
        <tiles:insertAttribute name="menu" />
    </div>
    <div id="footer">
        <tiles:insertAttribute name="footer" />
    </div>
</div>
</body>
</html>