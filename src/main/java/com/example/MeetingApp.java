package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MeetingApp extends Application {
    private final MeetingPlanner planner = new MeetingPlanner();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Meeting Planner");

        Color primaryColor = Color.web("#E53935");
        Color backgroundColor = Color.web("#FAFAFA");

        Label headerLabel = new Label("Meeting Planner");
        headerLabel.setFont(new Font("Arial Bold", 24));
        headerLabel.setTextFill(primaryColor);

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        titleField.setStyle("-fx-prompt-text-fill: gray; -fx-font-size: 14;");

        DatePicker datePicker = new DatePicker();
        TextField timeField = new TextField();
        timeField.setPromptText("Time (HH:MM)");
        timeField.setStyle("-fx-prompt-text-fill: gray; -fx-font-size: 14;");

        TextField placeField = new TextField();
        placeField.setPromptText("Place");
        placeField.setStyle("-fx-prompt-text-fill: gray; -fx-font-size: 14;");

        TextField participantsField = new TextField();
        participantsField.setPromptText("Participants (comma-separated)");
        participantsField.setStyle("-fx-prompt-text-fill: gray; -fx-font-size: 14;");

        Button addButton = new Button("Add Meeting");
        addButton.setStyle("-fx-background-color: #E53935; -fx-text-fill: white; -fx-font-size: 14;");
        addButton.setPrefHeight(40);

        TextField searchField = new TextField();
        searchField.setPromptText("Search by Date (YYYY-MM-DD)");
        searchField.setStyle("-fx-prompt-text-fill: gray; -fx-font-size: 14;");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #E53935; -fx-text-fill: white; -fx-font-size: 14;");
        searchButton.setPrefHeight(40);

        ListView<String> meetingList = new ListView<>();
        meetingList.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: lightgray;");

        addButton.setOnAction(e -> {
            try {
                String title = titleField.getText();
                LocalDateTime dateTime = LocalDateTime.of(datePicker.getValue(),
                        LocalDateTime.parse("2000-01-01T" + timeField.getText()).toLocalTime());
                String place = placeField.getText();
                String[] participants = participantsField.getText().split(",");

                planner.addMeeting(title, dateTime, place, Arrays.asList(participants));
                meetingList.getItems().add("Meeting: " + title + " on " + dateTime.toString());

                titleField.clear();
                timeField.clear();
                placeField.clear();
                participantsField.clear();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input");
                alert.show();
            }
        });

        searchButton.setOnAction(e -> {
            try {
                LocalDateTime searchDate = LocalDateTime.parse(searchField.getText() + "T00:00:00");
                meetingList.getItems().clear();
                planner.findMeetingsByDate(searchDate).forEach(m -> meetingList.getItems().add(m.toString()));
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid date format");
                alert.show();
            }
        });

        VBox inputSection = new VBox(10, titleField, datePicker, timeField, placeField, participantsField, addButton);
        VBox searchSection = new VBox(10, searchField, searchButton, meetingList);

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: lightgray; -fx-padding: 1;");

        VBox layout = new VBox(20, headerLabel, inputSection, separator, searchSection);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #FAFAFA;");

        primaryStage.setScene(new Scene(layout, 500, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
