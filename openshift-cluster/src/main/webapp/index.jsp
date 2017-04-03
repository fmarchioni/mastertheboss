<%@ page import="java.util.Enumeration,java.util.UUID" %>
<html>
    <body>
        <h1>Clustered Web</h1>

        <b>Node name:</b> <%= System.getProperty("jboss.node.name") %><br/>
        <b>Host name:</b> <%= System.getProperty("jboss.host.name") %><br/>
        <b>Session id:</b> <%= session.getId() %><br/>
        <h2> Just added random String to Session</h2>
        <br/>
        <h2>Session Data</h2>     
        <%
 
String k = UUID.randomUUID().toString();
String v = UUID.randomUUID().toString();
session.setAttribute(k,v); 
Enumeration keys = session.getAttributeNames();
while (keys.hasMoreElements())
{
  String key = (String)keys.nextElement();
  out.println(key + ": " + session.getValue(key) + "<br>");
}
        %>

      
    </body>
</html>
