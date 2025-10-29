package com.penimalla.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Problem> problems = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/localproblems", "sa", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM problems ORDER BY id DESC");
            while (rs.next()) {
                problems.add(new Problem(
                        rs.getInt("id"),
                        rs.getString("issueTitle"),
                        rs.getString("category"),
                        rs.getString("priority"),
                        rs.getString("mobile"),
                        rs.getString("area"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("reporter"),
                        rs.getString("imagePath"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                        ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("problems", problems);
        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);
    }
}
