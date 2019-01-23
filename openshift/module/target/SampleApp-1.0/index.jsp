<%@ page import="java.io.*, com.itextpdf.text.*" %> 
<html>
  <head>
    <title>Sample Application using iText</title>
  </head>

  <body bgcolor=white>

  <h1>Sample Application using iText</h1>
 

  <%

 response.setContentType("application/pdf");
        //Get the output stream for writing PDF object        
        OutputStream out=response.getOutputStream();
        try {
            Document document = new Document();
            
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Tutorial to Generate PDF using Servlet"));
            document.add(new Paragraph("PDF Created Using Servlet, iText Example Works"));
            document.close();
        }
                catch (DocumentException exc){
                throw new IOException(exc.getMessage());
                }
        finally {            
            out.close();
        }
%>

 

  </body>
</html> 
