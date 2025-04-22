package com.game.game_2048;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.GameView;
import controller.GameController;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameView gameView = new GameView(); // создаём визуальное представление
        GameController gameController = new GameController(gameView);
        gameController.initialize(); // логика старта

        Scene scene = new Scene(gameView.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon_2048.png")));
        primaryStage.getIcons().add(icon);
        primaryStage.show();

        gameView.getRoot().requestFocus();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> gameController.moveLeft();
                case RIGHT -> gameController.moveRight();
                case UP -> gameController.moveUp();
                case DOWN -> gameController.moveDown();
                case SPACE -> gameController.restartGame();
            }
        });
    }

    public static void main(String[] args) {
        launch(args); // запуск JavaFX
    }
}
