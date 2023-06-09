package com.example.signtest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Sora.ttf"), 14);
        System.out.println(Font.getFontNames("Sora"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 549);
        stage.setResizable(false);
        Image SignTest = new Image(this.getClass().getResourceAsStream("SignTest.png"));
        stage.getIcons().add(SignTest);
        stage.setTitle("Sign Test by Jason Huynh");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {
        launch();
    }
}