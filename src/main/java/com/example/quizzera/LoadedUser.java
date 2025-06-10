package com.example.quizzera;

public class LoadedUser {
    public  static int id;
    public static String username;
    public static String password;
    public static boolean isAdmin;
    public static int quizzesTaken;
    public static int maxScoreTakenTimes;
    public static int currentScoreInLoadedQuiz = 0;
    public static void loadUser(int id, String username, String password, boolean isAdmin, int quizzesTaken, int maxScoreTakenTimes){
        LoadedUser.id = id;
        LoadedUser.username = username;
        LoadedUser.password = password;
        LoadedUser.isAdmin = isAdmin;
        LoadedUser.quizzesTaken = quizzesTaken;
        LoadedUser.maxScoreTakenTimes = maxScoreTakenTimes;
    }
    public static void resetUser(){
        id = 0;
        username = "";
        password = "";
        isAdmin = false;
        quizzesTaken = 0;
        maxScoreTakenTimes = 0;
        currentScoreInLoadedQuiz = 0;
    }
    public static void resetCurrentScoreInLoadedQuiz(){
        currentScoreInLoadedQuiz = 0;
    }
}
