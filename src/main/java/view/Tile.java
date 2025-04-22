package view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Tile {
    private final StackPane pane;
    private final Rectangle background;
    private final Label label;

    public Tile(int value) {
        background = new Rectangle(100, 100);
        background.setArcWidth(15);
        background.setArcHeight(15);
        background.setFill(Color.BISQUE);
        label = new Label();
        label.setFont(Font.font(24));
        pane = new StackPane(background, label);
        setValue(value); // сразу установим внешний вид
    }

    public void setValue(int value) {
        label.setText(value == 0 ? "" : String.valueOf(value));
        background.setFill(getColor(value));
    }

    private Color getColor(int value) {
        return switch (value) {
            case 2 -> Color.BEIGE;
            case 4 -> Color.BISQUE;
            case 8 -> Color.LIGHTCORAL;
            case 16 -> Color.CORAL;
            case 32 -> Color.ORANGE;
            case 64 -> Color.DARKORANGE;
            case 128 -> Color.GOLD;
            case 256 -> Color.YELLOW;
            case 512 -> Color.LIGHTGREEN;
            case 1024 -> Color.GREEN;
            case 2048 -> Color.DARKGREEN;
            default -> Color.LIGHTGRAY;
        };
    }

    public StackPane getPane() {
        return pane;
    }
}
