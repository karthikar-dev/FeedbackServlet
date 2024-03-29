-- Create the feedback_db database
CREATE DATABASE feedback_db;

-- Switch to the feedback_db database
USE feedback_db;

-- Create the feedback table
CREATE TABLE feedback (
    -- Primary key column for unique identifier
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- Column for storing the name of the person providing feedback
    name VARCHAR(255) NOT NULL,
    -- Column for storing the name of the book associated with the feedback
    book_name VARCHAR(255) NOT NULL,
    -- Column for storing the feedback content
    feedback TEXT NOT NULL
);
