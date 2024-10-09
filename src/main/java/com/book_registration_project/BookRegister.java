package com.book_registration_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/register")
public class BookRegister extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String query = "INSERT INTO bookData (bookName, bookEdition, bookPrice) VALUES (?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        String bookName = request.getParameter("bookName");
        String bookEdition = request.getParameter("bookEdition");
        String bookPrice = request.getParameter("bookPrice");

        // Input validation
        if (bookName == null || bookName.isEmpty() || bookEdition == null || bookEdition.isEmpty() || bookPrice == null || bookPrice.isEmpty()) {
            pw.println("<h1>All fields are required.</h1>");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(bookPrice);
        } catch (NumberFormatException e) {
            pw.println("<h1>Error: Price must be a valid number.</h1>");
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            pw.println("<h1>Error loading database driver.</h1>");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_register", "root", "1111");
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, bookName);
            statement.setString(2, bookEdition);
            statement.setDouble(3, price);

            int count = statement.executeUpdate();

            if (count == 1) {
                pw.println("<h2>Record inserted successfully!</h2>");
            } else {
                pw.println("<h2>Record not inserted!</h2>");
            }
        } catch (SQLException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        } catch (Exception e) {
            pw.println("<h1>Unexpected error: " + e.getMessage() + "</h1>");
        }
        pw.println("<a class='btn' href='home.html'>Home</a>");
        pw.println("<br/>");
        pw.println("<a class='btn' href='bookListServlet'>Book List</a>");
    }
}
