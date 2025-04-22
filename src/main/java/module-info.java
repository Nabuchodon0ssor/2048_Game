module com.game.game_2048 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens com.game.game_2048 to javafx.fxml;
    exports com.game.game_2048;
    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}