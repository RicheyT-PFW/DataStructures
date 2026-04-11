module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens hw to javafx.fxml;
    exports hw;
}
