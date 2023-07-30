package com.mastertheboss.servlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
                 maxFileSize = 1024 * 1024 * 5,   // 5 MB
                 maxRequestSize = 1024 * 1024 * 10) // 10 MB
public class FileUploadServlet extends HttpServlet {
    private static String uploadPath=null;

    @Override
	public void init() throws ServletException{
        uploadPath= getServletContext().getInitParameter("upload.path");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        try {
            // Retrieve the file part from the request
            Part filePart = request.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            System.out.println(fileName);

            // Save the file to the server
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream, Paths.get(uploadPath + File.separator + fileName));
            System.out.println(uploadPath + File.separator + fileName);

            response.getWriter().println("File uploaded successfully!");
        } catch (IOException | ServletException e) {
            response.getWriter().println("File upload failed due to an error: " + e.getMessage());
        }
    }
}
