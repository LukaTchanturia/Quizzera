# Quizzერა 🎓

**A modern JavaFX quiz application with admin tools, AI-generated quizzes, user leaderboard, and community quiz creation.**

## 🚀 Features

- 🔐 **User Authentication**
    - Registration & Login with hashed passwords using BCrypt
    - Role-based access (Admin & User)

- 🧠 **Three Quiz Types**
    - Admin-created 
    - Community-created 
    - AI-generated quizzes using Gemini API based on user prompts

- 📊 **Leaderboard**
    - Tracks top-performing users based on quiz taken count and max score taken count

- 🛠️ **Quiz Management Tool**
    - Admins and milestone-unlocked users can create multi-question quizzes
    - Admins can delete any quiz from the system
    - Each question supports 4 options with one correct answer

- 🌐 **Cloud Database Support**
    - MySQL-compatible DB (Google Cloud SQL)
    - Data is synced across devices

- 🎨 **JavaFX UI**
    - Built with SceneBuilder
    - Custom CSS styling
    - Tooltip-enhanced components
    - Single-scene logic-based UI control (reduced scene switching)

## 🗂️ Technologies Used

- Java 17+
- JavaFX
- SceneBuilder
- MySQL (Cloud SQL)
- JDBC
- BCrypt
- Gemini API
- CSS

- IMPORTANT NOTE!!!!!!!!!!!!!!!!!!
- There is only one admin with the username of : "admin" and password : "admin123"
- If you want to log in in user account which has unlocked milestones, use account username2, password: password2