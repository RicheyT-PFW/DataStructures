package hw2;
/*
(100 Pts. Max, Harder) – Implemented based on tokens 
    When the space key is pressed, the current token is placed in the appropriate stack.
    Undo and redo operations provide tokens to and from the editor display. 
*/
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
     * 
     * Type any regular text to add content to the text window
     * Press CTRL + Z to undo, returning the text window contents to a previous state
     * Press CTRL + Y to redo, reverting the removed text from the last undo operation
     * 
     * If the current token is empty, backspace is not allowed.
     */
    @FXML
    void keyHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE && currentToken.isBlank()) {
            System.out.println("Space rejected");
            return;
        } else if (event.getCode() == KeyCode.SPACE) {
            System.out.println("Space");
            writingLbl.setText(writingLbl.getText() + " ");
            undoStack.push(currentToken);
            undoStackLbl.setText(undoStack.toString());
            currentToken = "";
            tokenLbl.setText(currentToken);
            int temp = Integer.parseInt(countLbl.getText());
            countLbl.setText(String.valueOf(temp + 1));
            return;
        }

        if (event.getCode() == KeyCode.BACK_SPACE && currentToken.isBlank()) {
            System.out.println("Backspace rejected");
            return;
        } else if (event.getCode() == KeyCode.BACK_SPACE) {
            currentToken = currentToken.substring(0, currentToken.length() - 1);
            writingLbl.setText(writingLbl.getText().substring(0, writingLbl.getText().length() - 1));
            System.out.println("Backspace");
        }

        if (event.getCode() == KeyCode.DELETE && currentToken.isBlank()) {
            System.out.println("Delete rejected");
            return;
        } else if (event.getCode() == KeyCode.DELETE) {
            currentToken = currentToken.substring(0, currentToken.length() - 1);
            writingLbl.setText(writingLbl.getText().substring(0, writingLbl.getText().length() - 1));
            System.out.println("Delete");
        }

        if (event.getCode() == KeyCode.Z && event.isControlDown()) {
            if (!undoStack.isEmpty()) {
                if (!currentToken.isBlank()) {
                    writingLbl.setText(writingLbl.getText().substring(0, writingLbl.getText().length() - currentToken.length()));
                    currentToken = "";
                    tokenLbl.setText(currentToken);
                }
                String token = undoStack.pop();
                redoStack.push(token);
                writingLbl.setText(writingLbl.getText().substring(0, writingLbl.getText().length() - token.length() - 1));
                undoStackLbl.setText(undoStack.toString());
                redoStackLbl.setText(redoStack.toString());
                int temp = Integer.parseInt(countLbl.getText()) - 1;
                countLbl.setText(String.valueOf(temp));
                currentToken = "";
                tokenLbl.setText(currentToken);
                System.out.println("Undo");
            }
            return;
        }

        if (event.getCode() == KeyCode.Y && event.isControlDown()) {
            if (!redoStack.isEmpty()) {
                String token = redoStack.pop();
                undoStack.push(token);
                String current = writingLbl.getText().trim();
                if (current.isEmpty()) {
                    writingLbl.setText(token + " ");
                } else {
                    writingLbl.setText(current + " " + token + " ");
                }
                undoStackLbl.setText(undoStack.toString());
                redoStackLbl.setText(redoStack.toString());
                int temp = Integer.parseInt(countLbl.getText()) + 1;
                countLbl.setText(String.valueOf(temp));
                currentToken = "";
                tokenLbl.setText(currentToken);
                System.out.println("Redo");
            }
            return;
        }

        writingLbl.setText(writingLbl.getText() + event.getText());
        currentToken += event.getText();
        tokenLbl.setText(currentToken);

        while (!redoStack.isEmpty()) {
            redoStack.pop();
            redoStackLbl.setText(redoStack.toString());
        }
    }

    @FXML
    void initialize() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        writingLbl.setText("");
        countLbl.setText("0");
        tokenLbl.setText("");
        currentToken = "";
        undoStackLbl.setText(undoStack.toString());
        redoStackLbl.setText(redoStack.toString());
    }

}
