package view;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import util.SoundPlayer;


public class GameView {
    private static final int SIZE = 4;
    private final Tile[][] tiles = new Tile[SIZE][SIZE];
    private Label scoreLabel;

    private GridPane grid;
    private final VBox root;
    private HBox topPanel;

    public GameView() {
        initTopPanel();
        initGrid();
        root = new VBox(topPanel, grid);
    }

    private void initTopPanel(){
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font(18));

        Button soundButton = new Button("🔊 Sound: On");

        soundButton.setOnAction(e -> {
            boolean enabled = !SoundPlayer.isSoundEnabled();
            SoundPlayer.setSoundEnabled(enabled);
            soundButton.setText(enabled ? "🔊 Sound: On" : "🔇 Sound: Off");

            soundButton.getScene().getRoot().requestFocus();
        });

        topPanel = new HBox(scoreLabel, soundButton);
        topPanel.setSpacing(10);
        topPanel.setStyle("-fx-padding: 10;");
    }

    private void initGrid() {
        grid = new GridPane();
        grid.setHgap(5); // горизонтальный промежуток между плитками
        grid.setVgap(5); // вертикальный промежуток между плитками
        grid.setStyle("-fx-background-color: #bbada0; -fx-padding: 10;"); // фон игрового поля

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Tile tile = new Tile(0);
                tiles[y][x] = tile;
                grid.add(tile.getPane(), x, y);
            }
        }
    }

    public Parent getRoot() {
        return root;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

}
