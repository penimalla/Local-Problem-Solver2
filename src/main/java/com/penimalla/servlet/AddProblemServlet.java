package com.penimalla.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 10)
public class AddProblemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String issueTitle = request.getParameter("issueTitle");
        String category = request.getParameter("category");
        String priority = request.getParameter("priority");
        String mobile = request.getParameter("mobile");
        String area = request.getParameter("area");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String reporter = request.getParameter("reporter");
        String latitudeStr = request.getParameter("latitude");
        String longitudeStr = request.getParameter("longitude");

        double latitude = latitudeStr != null ? Double.parseDouble(latitudeStr) : 0;
        double longitude = longitudeStr != null ? Double.parseDouble(longitudeStr) : 0;

        String imagePath = "";
        Part filePart = request.getPart("problemImage");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/uploads/");
            Files.createDirectories(Paths.get(uploadDir));
            String savePath = uploadDir + File.separator + System.currentTimeMillis() + "_" + fileName;
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, Paths.get(savePath), StandardCopyOption.REPLACE_EXISTING);
            }
            imagePath = "uploads/" + System.currentTimeMillis() + "_" + fileName;
        }

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/localproblems", "sa", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS problems (id INT AUTO_INCREMENT PRIMARY KEY, issueTitle VARCHAR(255), category VARCHAR(100), priority VARCHAR(20), mobile VARCHAR(20), area VARCHAR(100), location VARCHAR(255), description VARCHAR(1000), reporter VARCHAR(100), imagePath VARCHAR(255), latitude DOUBLE, longitude DOUBLE)"
            );
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO problems(issueTitle, category, priority, mobile, area, location, description, reporter, imagePath, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, issueTitle);
            ps.setString(2, category);
            ps.setString(3, priority);
            ps.setString(4, mobile);
            ps.setString(5, area);
            ps.setString(6, location);
            ps.setString(7, description);
            ps.setString(8, reporter);
            ps.setString(9, imagePath);
            ps.setDouble(10, latitude);
            ps.setDouble(11, longitude);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("dashboard");
    }
}
