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

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String query = "SELECT bookName, bookEdition, bookPrice FROM bookData WHERE bookId=?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        // Get the id of the record
        int id = Integer.parseInt(request.getParameter("id"));

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
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                pw.println("<form action='editurl?id=" + id + "' method='post'>");
                pw.println("<table align='center'>");
                pw.println("<tr>");
                pw.println("<td>Book Name</td>");
                pw.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Book Edition</td>");
                pw.println("<td><input type='text' name='bookEdition' value='" + rs.getString(2) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Book Price</td>");
                pw.println("<td><input type='text' name='bookPrice' value='" + rs.getDouble(3) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td><input type='submit' value='Edit'></td>");
                pw.println("<td><input type='reset' value='Cancel'></td>");
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("</form>");
            } else {
                pw.println("<h1>No record found for the provided ID.</h1>");
            }
        } catch (SQLException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        } catch (Exception e) {
            pw.println("<h1>Unexpected error: " + e.getMessage() + "</h1>");
        }
        pw.println("<a href='home.html'>Home</a>");
    }
}
