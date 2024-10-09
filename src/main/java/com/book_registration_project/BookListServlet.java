package com.book_registration_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookListServlet")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String query = "SELECT bookId, bookName, bookEdition, bookPrice FROM bookData";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        // Load JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            pw.println("<h1>Error loading database driver.</h1>");
            return;
        }

        // Use try-with-resources for better resource management
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_register", "root", "1111");
             PreparedStatement statement = con.prepareStatement(query)) {

            ResultSet rs = statement.executeQuery();
            pw.println("<table align=center border='1' style='margin-top:100px;'");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Book Edition</th>");
            pw.println("<th>Book Price</th>");
            pw.println("<th> edit </th>");
            pw.println("<th> delete </th>");
            pw.println("</tr>");
            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt("bookId") + "</td>");
                pw.println("<td>" + rs.getString("bookName") + "</td>");
                pw.println("<td>" + rs.getString("bookEdition") + "</td>");
                pw.println("<td>" + rs.getDouble("bookPrice") + "</td>");
                pw.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>edit</a></td>");
                pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>delete</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
        } catch (SQLException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        } catch (Exception e) {
            pw.println("<h1>Unexpected error: " + e.getMessage() + "</h1>");
        }
        pw.println("<a style='display:inline-block; margin:20px auto 0px;' href='home.html'>Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Optionally, you could also implement doPost if you need to handle POST requests.
        // Currently, it's not used in this servlet.
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "POST method not supported.");
    }
}
