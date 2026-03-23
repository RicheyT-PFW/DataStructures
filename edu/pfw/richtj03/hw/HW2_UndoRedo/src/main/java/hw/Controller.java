package hw;
/**
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
        // If the spacebar is pressed when the current token is blank
        if(event.getCode() == KeyCode.SPACE && currentToken.isBlank()) {
            System.out.println("Space rejected");
            return;
            // If the spacebar is pressed when the current token is not blank
        } else if(event.getCode() == KeyCode.SPACE && !currentToken.isBlank()) {
            System.out.println("Space");
            undoStack.push(event.getText());
            undoStacklbl.text = undoStack.toString();
            currentToken = "";
            return;
        }

        // If the backspace key is pressed when the current token is blank
        if(event.getCode() == KeyCode.BACK_SPACE && currentToken.isBlank()) {
            System.out.println("Backspace rejected");
            return;
            // If the backspace key is pressed when the current token is not blank
        } else if(event.getCode() == KeyCode.BACK_SPACE && !currentToken.isBlank()) {
            
        }
        
        // If the delete key is pressed when the current token is blank
        if(event.getCode() == KeyCode.DELETE && currentToken.isBlank()) {
            System.out.println("Delete rejected");
            return;
        } else if(event.getCode() == KeyCode.DELETE && !currentToken.isBlank()) {

        }
        

        if(event.getCode() == KeyCode.Z && isControlDown()) {
            writinglbl += undoStack.pop();
            undoStacklbl.text = undoStack.toString();
            currentToken = "";
        } else if(event.getCode() == KeyCode.Z && !isControlDown()) {
            writinglbl += event.getText();
            currentToken += event.getText();
            tokenlbl.text = currentToken;
            
        }
        
    }

    @FXML
    void initialize() {
        writinglbl.text = "";
        countlbl.text = "0";
        tokenlbl.text = "";
        undoStacklbl.text = undoStack.toString();
        redoStacklbl.text = redoStack.toString();
    }

}
