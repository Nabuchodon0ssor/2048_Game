package controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.GameModel;
import util.SoundPlayer;
import view.GameView;
import view.Tile;

public class GameController {
    private final GameModel model;
    private final GameView view;


    public GameController(GameView view) {
        this.view = view;
        this.model = new GameModel();

    }


    public void initialize() {
        model.reset();
        updateView();
    }

    private void updateView() {
        int[][] values = model.getValues();
        Tile[][] tiles = view.getTiles();

        for (int y = 0; y < values.length; y++) {
            for (int x = 0; x < values[y].length; x++) {
                tiles[y][x].setValue(values[y][x]);
            }
        }

        view.updateScore(model.getScore());
    }

    public void moveLeft() {
        if (model.moveLeft()) {
            SoundPlayer.play("move.wav");
            updateView();

            if (model.has2048Tile()) {
                SoundPlayer.play("win.wav");
                showAlert("You Won", "YOU WON! :)");
                return;
            }

            if (!model.canUserMove()) {
                SoundPlayer.play("lose.wav");
                showAlert("Game Over", "GAME OVER!");
            }
        }
    }

    public void moveRight() {
        if (model.moveRight()) {
            SoundPlayer.play("move.wav");
            updateView();

            if (model.has2048Tile()) {
                SoundPlayer.play("win.wav");
                showAlert("You Won", "YOU WON! :)");
                return;
            }

            if (!model.canUserMove()) {
                SoundPlayer.play("lose.wav");
                showAlert("Game Over", "GAME OVER!");
            }
        }
    }

    public void moveUp() {
        if (model.moveUp()) {
            SoundPlayer.play("move.wav");
            updateView();

            if (model.has2048Tile()) {
                SoundPlayer.play("win.wav");
                showAlert("You Won", "YOU WON! :)");
                return;
            }

            if (!model.canUserMove()) {
                SoundPlayer.play("lose.wav");
                showAlert("Game Over", "GAME OVER!");
            }
        }
    }

    public void moveDown() {
        if (model.moveDown()) {
            SoundPlayer.play("move.wav");
            updateView();

            if (model.has2048Tile()) {
                SoundPlayer.play("win.wav");
                showAlert("You Won", "YOU WON! :)");
                return;
            }

            if (!model.canUserMove()) {
                SoundPlayer.play("lose.wav");
                showAlert("Game Over", "GAME OVER!");
            }
        }
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public void restartGame() {
        model.reset();
        updateView();
    }

}
