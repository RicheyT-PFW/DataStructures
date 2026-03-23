package hw;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.EmptyStackException;

public class Controller {

    @FXML
    private Label countLbl;

    @FXML
    private Label undoStackLbl;
    @FXML
    private Label redoStackLbl;

    @FXML
    private Label tokenLbl;

    @FXML
    private Label writingLbl;

    private Stack<String> undoStack;
    private Stack<String> redoStack;

    private String currentToken;

    /**
     * @param event
     */
    @FXML
    void keyHandler(KeyEvent event) {

    }

    @FXML
    void initialize() {

    }

}
