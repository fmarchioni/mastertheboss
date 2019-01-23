<%@ page import="java.io.*, com.itextpdf.text.*,com.itextpdf.text.pdf.*" %> 
<html>
  <head>
    <title>Sample Application using iText</title>
  </head>

  <body bgcolor=white>

  <h1>Sample Application using iText</h1>
 

  <%

        response.setContentType("application/pdf");
        //Get the output stream for writing PDF object        
        
        try {
            Document document = new Document();
            
            PdfWriter.getInstance(document, response.getOutputStream());
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
