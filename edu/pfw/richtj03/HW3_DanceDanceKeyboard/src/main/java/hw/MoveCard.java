package hw;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class MoveCard extends ImageView {

    public static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT", "A", "D"};
    public static final double SIZE = 80;

    private static final Map<String, Image> IMAGES = new HashMap<>();

    //Load the symbol images
    static {
        for (String direction : DIRECTIONS) {
            String filename = direction + ".png";
            IMAGES.put(direction, new Image(MoveCard.class.getResourceAsStream(filename)));
        }
    }

    private String direction;
    private TranslateTransition transition;
    private double score; //Score assigned when the card is generated based on multiplier

    public MoveCard(String direction, double score) {
        super(IMAGES.get(direction));
        this.direction = direction;
        this.score = score;
        //Sizing
        setFitHeight(SIZE);
        setFitWidth(SIZE);
        setPreserveRatio(true);
    }

    public boolean isMatch(String inputKey) {
        return direction.equals(inputKey);
    }

    public String getDirection() {
        return direction;
    }

    //Score to award when the card is matched
    public double getScore() {
        return score;
    }

    //Keep track of the transition to stop it when the card is matched
    public void setTransition(TranslateTransition transition) {
        this.transition = transition;
    }
    public void stop(){
        transition.stop();
    }
}
