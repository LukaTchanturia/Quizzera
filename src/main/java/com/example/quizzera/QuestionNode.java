package com.example.quizzera;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionNode extends VBox {
    private TextField questionField;
    private TextField[] answerFields = new TextField[4];
    private RadioButton[] correctButtons = new RadioButton[4];
    private ToggleGroup correctToggleGroup = new ToggleGroup();

    public QuestionNode(int questionNumber){
        this.getStyleClass().add("question-card");
        setSpacing(5);
        setStyle("-fx-border-color: transparent; -fx-border-radius: 10; -fx-background-color: transparent; -fx-background-radius: 10;");
        Label questionLabel = new Label("Question " + questionNumber + ":");

        questionField = new TextField();
        questionField.setPromptText("Enter your question here");
        questionField.setStyle("question-card");
        getChildren().addAll(questionLabel, questionField);

        for (int i = 0; i < 4; i++) {
            HBox answerRow = new HBox(10);
            answerRow.setPadding(new Insets(2, 0, 2, 0));

            answerFields[i] = new TextField();
            answerFields[i].setPromptText("Answer option " + (i + 1));
            answerFields[i].setPrefWidth(400);

            correctButtons[i] = new RadioButton("Correct");
            correctButtons[i].setToggleGroup(correctToggleGroup);

            answerRow.getChildren().addAll(answerFields[i], correctButtons[i]);
            getChildren().add(answerRow);
        }

    }

    public String getQuestionText() {
        return questionField.getText().trim();
    }

    public String[] getAnswers() {
        String[] answers = new String[4];
        for (int i = 0; i < 4; i++) {
            answers[i] = answerFields[i].getText().trim();
        }
        return answers;
    }

    public int getCorrectAnswerIndex() {
        for (int i = 0; i < 4; i++) {
            if (correctButtons[i].isSelected()) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValid() {
        if (getQuestionText().isEmpty()) return false;
        for (String ans : getAnswers()) {
            if (ans.isEmpty()) return false;
        }
        return getCorrectAnswerIndex() != -1;
    }


}
