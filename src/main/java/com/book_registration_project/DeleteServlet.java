package com.book_registration_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String query = "DELETE FROM bookData WHERE bookId=?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id"));

        // Load JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_register", "root", "1111");
                 PreparedStatement statement = con.prepareStatement(query)) {
                statement.setInt(1, id);
                int count = statement.executeUpdate();

                if (count == 1) {
                    pw.println("<h2>Record deleted successfully!</h2>");
                } else {
                    pw.println("<h2>Record not deleted.</h2>");
                }
            }
        } catch (SQLException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        } catch (Exception e) {
            pw.println("<h1>Unexpected error: " + e.getMessage() + "</h1>");
        }
        pw.println("<a href='home.html'>Home</a>");
        pw.println("<br/>");
        pw.println("<a href='bookListServlet'>Book List</a>");
    }
}
