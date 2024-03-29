# FeedbackServlet

The **FeedbackServlet** is a Java web application that allows users to submit feedback through a web form. The feedback data is stored in a MySQL database. Upon successful submission, the application responds with a "feedback saved" message.

## Features

- Simple and user-friendly feedback form.
- Data validation ensures all required fields are filled before submission.
- Secure handling of user input to prevent SQL injection and other security vulnerabilities.
- Feedback data stored in a MySQL database for future reference and analysis.

## Prerequisites

Before running the FeedbackServlet application, ensure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven
- MySQL Server
- MySQL Connector/J (Java MySQL Connector)
- HTML/CSS for frontend
- Bootstrap for styling
- JavaScript for client-side interactions (Fetch API for asynchronous form submission)

## Setup

1. Clone this repository to your local machine:

```
git clone https://github.com/example/FeedbackServlet.git
```

2. Configure the database connection properties in the `db.properties` file located in the `src/main/resources` directory. Modify the following properties as per your MySQL database setup:

```
jdbc.url=jdbc:mysql://localhost:3306/feedback_db
jdbc.user=username
jdbc.password=password
```

3. Create a MySQL database named `feedback_db`:

```sql
CREATE DATABASE feedback_db;
USE feedback_db;

CREATE TABLE feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    book_name VARCHAR(255) NOT NULL,
    feedback_text TEXT NOT NULL
);
```

4. Build the project using Maven:

```
mvn clean package
```

5. Deploy the generated WAR file to a servlet container (e.g., Apache Tomcat).

6. Access the application by navigating to `http://localhost:8080/feedback-form.html` in your web browser.

## Usage

1. Access the application through your web browser.
2. Fill out the feedback form with your name, book name, and feedback.
3. Click the "Submit" button to submit your feedback.
4. Upon successful submission, you will receive a confirmation message.

## Folder Structure

```

    FeedbackServlet/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── feedback/
    │   │   │           └── servlet/
    │   │   │               └── FeedbackServlet.java
    │   │   └── resources/
    │   │       └── db.properties
    │   │       └── db.sql
    │   └── test/
    │       └── java/
    ├── webapp/
    │   ├── WEB-INF/
    │   │   └── web.xml
    │   └── feedback-form.html 
    ├── pom.xml    
    └── README.md

```
## Contribution

Contributions are welcome! If you have any suggestions or improvements, feel free to open an issue or create a pull request.


## License

This project is licensed under the MIT License. Feel free to use and modify it as per your requirements.

---

Feel free to customize and extend this application according to your needs! If you encounter any issues or have suggestions for improvement, please [submit an issue](https://github.com/karthikar-dev/FeedbackServlet/issues). We welcome contributions from the community!
