 
       <%@page import="java.io.*, com.mastertheboss.rest.Config" %>
        <%@page import="java.util.*" %> 
        <%!        public void GetDirectory(String a_Path, Vector a_files, Vector a_folders) {
                File l_Directory = new File(a_Path);
                File[] l_files = l_Directory.listFiles();

                for (int c = 0; c < l_files.length; c++) {
                    if (l_files[c].isDirectory()) {
                        a_folders.add(l_files[c].getName());
                    } else {
                        a_files.add(l_files[c].getName());
                    }
                }


            }
        %> 


<html>
<head>

    <link rel="stylesheet" type="text/css" href="stylesheet.css" media="screen" />





<title>Start Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body ng-app='app' ng-controller='ctrl'>
    <h1>REST Upload/Download demo using pure JSP</h2>
	<h2>Upload File</h2>
	<form method="post" action="rest/file/upload"
		enctype="multipart/form-data">
		<input type="hidden" name="action" value="upload" /> <label>Load
			your file:</label> <input type="file" name="attachment" /> <br /> <input
			type="submit" value="Upload file" />
	</form>

	<h2>Download File</h2>
	<form method="POST" action="rest/file/download">
		File name: <input type="text" name="file"> 
		<input type="submit">


	</form>
	
	<h2>Current Directory <%=Config.UPLOAD_FOLDER%>:</h2>
	<br/>
	        <%
            Vector l_Files = new Vector(), l_Folders = new Vector();
            GetDirectory(Config.UPLOAD_FOLDER, l_Files, l_Folders);
            out.println("<ul>");
            for (int a = 0; a < l_Files.size(); a++) {
                out.println("<li>" + l_Files.elementAt(a).toString() + "</li>");
            }
            out.println("</ul>");
           
        %>



</body>
</html>

