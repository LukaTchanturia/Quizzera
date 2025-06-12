-- Create and use the database
CREATE DATABASE IF NOT EXISTS quizzera;
USE quizzera;

-- USERS TABLE
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_admin BOOLEAN DEFAULT FALSE,
    quizzes_taken INT DEFAULT 0,
    max_score_taken_times INT DEFAULT 0
);
INSERT INTO users (username, password, is_admin, quizzes_taken, max_score_taken_times) VALUES
('admin', '$2a$10$dTNmL6IbLaRcssJvUmJ3q.2a/.8AS..756x16PuKeFbhk.YJtr6Wm', TRUE, 0, 0);

-- QUIZZES TABLE
CREATE TABLE IF NOT EXISTS quizzes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    created_by VARCHAR(50) NOT NULL
);

-- QUESTIONS TABLE
CREATE TABLE IF NOT EXISTS questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quiz_id INT,
    question_text VARCHAR(200),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
);

-- ANSWERS TABLE
CREATE TABLE IF NOT EXISTS answers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question_id INT,
    answer_text VARCHAR(200),
    is_correct BOOLEAN,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);


