package com.example.quizzera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoadedQuiz {
    public static int id;
    public static String title;
    public static ArrayList<String> questions = new ArrayList<>();
    public static int currentQuestionNumber;
    public static ArrayList<String> answers = new ArrayList<>();
    public static ArrayList<String> correctAnswers = new ArrayList<>();
    public static String created_by = "";

    public static void resetQuiz(){
        id = 0;
        title = "";
        questions.clear();
        currentQuestionNumber = 0;
        answers.clear();
        correctAnswers.clear();
        created_by = "";
    }
}
