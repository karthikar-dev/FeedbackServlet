package com.feedback.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST request to submit feedback.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String name = request.getParameter("name");
        String bookName = request.getParameter("bookName");
        String feedback = request.getParameter("feedback");

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check for missing parameters
        if (name == null || name.isEmpty() || bookName == null || bookName.isEmpty() || feedback == null || feedback.isEmpty()) {
            // Handle missing parameter(s)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("Missing required parameter.");
            return;
        }

        // Load database properties from db.properties file
        Properties properties = new Properties();
        try (InputStream inStream = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (inStream != null) {
                properties.load(inStream);
            } else {
                throw new IOException("Unable to load db.properties");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            out.println("<h2>Error: Unable to load database properties</h2>");
            return;
        }

        // Get database connection properties
        String jdbcUrl = properties.getProperty("jdbc.url");
        String jdbcUser = properties.getProperty("jdbc.user");
        String jdbcPassword = properties.getProperty("jdbc.password");

        try {
            // Load JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection conn = getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            // Prepare SQL statement
            String sql = "INSERT INTO feedback (name, book_name, feedback) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, bookName);
            statement.setString(3, feedback);

            // Execute SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Feedback saved successfully
                response.setStatus(HttpServletResponse.SC_OK);
                out.print("Feedback saved successfully!");
            } else {
                // Failed to save feedback
                out.print("Failed to save feedback.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle database connection or query errors
            out.print("Error: " + e.getMessage());
        }
    }
}
