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

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String query = "UPDATE bookData SET bookName=?, bookEdition=?, bookPrice=? WHERE bookId=?";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        // Get the edited data
        int id = Integer.parseInt(request.getParameter("id")); // Ensure the ID is passed
        String bookName = request.getParameter("bookName");
        String bookEdition = request.getParameter("bookEdition");
        double bookPrice = Double.parseDouble(request.getParameter("bookPrice"));

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
            statement.setString(1, bookName);
            statement.setString(2, bookEdition);
            statement.setDouble(3, bookPrice);
            statement.setInt(4, id);

            int count = statement.executeUpdate();

            if (count == 1) {
                pw.println("<h2>Record edited successfully!</h2>");
            } else {
                pw.println("<h2>Record not edited.</h2>");
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
