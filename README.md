# Book Management JSP Servlet JDBC CRUD Example

## Project Overview

This project is a Book Management System that allows users to perform CRUD (Create, Read, Update, Delete) operations on book records using JSP (JavaServer Pages) and Servlets with JDBC (Java Database Connectivity). The application provides a simple web interface for managing books in a library.

## Features

- **Add New Books**: Users can add new books to the database with details such as title, author, and publication date.
- **View Books**: A list of all books in the database is displayed, allowing users to see all available records.
- **Update Book Information**: Users can edit existing book records to update their details.
- **Delete Books**: Users can remove books from the database.
- **Responsive Design**: The web interface is designed to be user-friendly and responsive.

## Technologies Used

- **Java**: For backend development using Servlets and JSP.
- **JDBC**: For database connectivity and operations.
- **MySQL**: As the database for storing book records.
- **HTML/CSS**: For the frontend layout and styling.

## Getting Started

### Prerequisites

- JDK (Java Development Kit)
- Apache Tomcat (or any other servlet container)
- MySQL Server
- IDE (e.g., Eclipse, IntelliJ IDEA)

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/MaheshDataWizard/book-management-jsp-servlet-jdbc-crud-example.git
   cd book-management-jsp-servlet-jdbc-crud-example
   ```

2. **Set Up MySQL Database**:
   - Create a new database named `book_management`.
   - Execute the SQL scripts to create the required tables.

3. **Configure Database Connection**:
   - Update the database connection parameters in the `web.xml` file or relevant configuration files.

4. **Deploy on Apache Tomcat**:
   - Deploy the project on an Apache Tomcat server.
   - Start the server and access the application via a web browser.

## Usage

- Navigate to the home page to view all books.
- Use the provided forms to add, update, or delete books as needed.

## Contributing

Contributions are welcome! If you have suggestions for improvements or find bugs, please open an issue or submit a pull request.

## Acknowledgments

- Thanks to the [MySQL Documentation](https://dev.mysql.com/doc/) for providing resources on database setup and configuration.
- Inspiration from various online tutorials and documentation for JSP, Servlets, and JDBC.
