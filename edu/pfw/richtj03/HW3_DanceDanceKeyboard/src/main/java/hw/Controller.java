package hw;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Random;

/**
 * Name:
 * Username:
 */

public class Controller {

    @FXML
    private Label scoreLbl;
    private double score = 0;
    private double basePoints = 100;
    private double specialMultiplier = 2.0;

    @FXML
    private Label multiplierLbl;
    private double comboMultiplier = 1.0;

    @FXML
    private Label mistakeLbl;

    @FXML
    private Pane gamePane;
    private final double paneWidth = 900;
    private final double paneHeight = 500;

    private final double barY = 400;

    private double duration = 5000; //5000 initial
    private double durationDecrease = 50;
    private double delay = 800;
    private double specialCardChance = 0.15;

    private int mistakesLeft = 10;
    private PriorityQueue<MoveCard> pqueue;

    private final HashMap<KeyCode, Double> keyLocations = new HashMap<>();

    Timeline cardSpawnTimeline;

    private Random random = new Random();

    //Music
    MediaPlayer mediaPlayer;

    @FXML
    void initialize() {
        //Make rhythm bar
        Line rhythmBar = new Line(0, barY, paneWidth, barY);
        rhythmBar.setStrokeWidth(4);
        rhythmBar.setStroke(Color.CORNFLOWERBLUE);
        gamePane.getChildren().add(rhythmBar);

        mistakeLbl.setText(String.valueOf(mistakesLeft));
        multiplierLbl.setText(String.valueOf(comboMultiplier));

        //Add each normal arrow MoveCard X location to the hashmap
        keyLocations.put(KeyCode.UP, 150.0);
        keyLocations.put(KeyCode.DOWN, 300.0);
        keyLocations.put(KeyCode.LEFT, 450.0);
        keyLocations.put(KeyCode.RIGHT, 600.0);

        //TODO Initialize priority queue
        pqueue = new PriorityQueue<>(2);

        //Game loop to spawn cards
        cardSpawnTimeline = new Timeline();
        //Initialize timeline
        cardSpawnTimeline.setCycleCount(Timeline.INDEFINITE);
        cardSpawnTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), timeEvent -> {
            generateCard();
        }));
        cardSpawnTimeline.play();

        //Play music
 //       Media music = new Media(getClass().getResource("music.mp3").toExternalForm());
   //     mediaPlayer = new MediaPlayer(music);
     //   mediaPlayer.play();
    }

    @FXML
    void keyHandler(KeyEvent event) {
       //TODO
        System.out.print("Key Value: " + event.getCode().toString());
        //Check next card in the priority queue
         MoveCard temp = pqueue.peek();
        //If the card matches the key pressed and is near the bar, remove it and increase score
         if(temp.isMatch(event.getCode().toString()) && nearBar(temp)) {
             //Fade out the card
             FadeTransition fade = new FadeTransition();
             fade.setDuration(Duration.millis(100));
             fade.setFromValue(1.0);
             fade.setToValue(0.0);
             fade.setNode(temp);
             fade.play();


           //  Remove the card after fading
             fade.setOnFinished(finishEvent -> {
                 System.out.println(temp.getDirection() + " is fading.");
                 gamePane.getChildren().remove(temp);
             });

             //Increase score by multiplier
             score += temp.getScore() * comboMultiplier;
             scoreLbl.setText(String.valueOf((int)score));

             //Increase combo multiplier
             comboMultiplier += 0.1;
             multiplierLbl.setText(String.format("%.1f", comboMultiplier));

             //Take card from priority queue
             pqueue.dequeue();

             //Increase speed of future generated cards
             duration -= durationDecrease;
         } //If the card does not match the key pressed, penalize the player
            else {
            mistake(temp);
         }
    }


 public void generateCard() {
     //Determine special or normal card
     boolean isSpecial = false;
     if(random.nextFloat() < specialCardChance) {
         isSpecial = true;
     }

     MoveCard card;

     if (isSpecial) {
         String dir = random.nextBoolean() ? "A" : "D";
         card = new MoveCard(dir, basePoints * specialMultiplier);



         if (dir.equals("A")) {
             card.setLayoutX(0);
         } else {
             card.setLayoutX(paneWidth - MoveCard.SIZE);
         }
     } else {
         String direction = MoveCard.DIRECTIONS[random.nextInt(4)];
         card = new MoveCard(direction, basePoints * comboMultiplier);

         double x = keyLocations.get(KeyCode.valueOf(direction));
         card.setLayoutX(x);
         card.setLayoutY(0);
     }

     //Animate the card movement
     TranslateTransition move = new TranslateTransition();
     move.setNode(card);
     move.setDuration(Duration.millis(duration));

     //Special card moves side to side
     if (isSpecial) {
        move.setFromY(barY - 60);
        move.setToY(barY - 60);

         if (card.getDirection().equals("A")) {
             card.setLayoutX(0);
             move.setFromX(0);
             move.setToX(paneWidth - MoveCard.SIZE);
         } else {
             card.setLayoutX(paneWidth - MoveCard.SIZE);
             move.setFromX(0);
             move.setToX(-(paneWidth - MoveCard.SIZE));
         }
     } else { //Normal card moves down
         move.setFromY(0);
         move.setToY(barY);
     }

     //When an unpressed card reaches the bottom of the screen, remove it and penalize the player
     move.setOnFinished(finishEvent -> {

            System.out.println("Card " + card.hashCode() + " has finished.");
             // Card was missed, so penalize
             mistake(card);

             // Wiggle animation
             ScaleTransition wiggle = new ScaleTransition(Duration.millis(100), card);
             wiggle.setByX(-0.3);
             wiggle.setByY(-0.3);
             wiggle.setCycleCount(3);
             wiggle.setAutoReverse(true);
             wiggle.setOnFinished(wiggleEvent -> gamePane.getChildren().remove(card));
             wiggle.play();

             // Remove from queue
             pqueue.dequeue();

             // Card was already hit and removed, just remove from pane if still there
             gamePane.getChildren().remove(card);

     });

     //Start card movement, set card transition, add card to UI and priority queue
     card.setTransition(move);
     gamePane.getChildren().add(card);


     if (isSpecial) {
         pqueue.enqueue(card, 2);
     } else {
         pqueue.enqueue(card, 1);
     }

     move.play();
 }


    //Returns true if the card is near enough to the bar to be considered a match
    public boolean nearBar(MoveCard card) {
        System.out.println(
                "\n\tCard: " + card.hashCode() +
                "\n\tDirection: " + card.getDirection() +
                "\n\tResult: " +  (card.getTranslateY() >= barY - MoveCard.SIZE)
        );
        return card.getTranslateY() >= barY - MoveCard.SIZE;
    }

    private void mistake(MoveCard card) {
     System.out.println("Mistake Card: " + card.hashCode());
     mistake();
    }

    //Penalize the player for missing a card or pressing the wrong key
    public void mistake(){
        //TODO
        //Lose points, reset multiplier, slow down card generation
        score -= basePoints;
        if (score < 0) score = 0;
        scoreLbl.setText(String.valueOf((int)score));

        // Reset multiplier
        comboMultiplier = 1.0;
        multiplierLbl.setText(String.valueOf(comboMultiplier));

        // Slow down card generation
        duration += durationDecrease;

        // Decrease mistakes left
        if(mistakesLeft > 0) {
            mistakesLeft--;
            mistakeLbl.setText(String.valueOf(mistakesLeft));

            //Pulse the mistake label
            ScaleTransition pulse = new ScaleTransition(Duration.millis(100), mistakeLbl);
            pulse.setByX(0.3);
            pulse.setByY(0.3);
            pulse.setCycleCount(4);
            pulse.setAutoReverse(true);
            pulse.play();
        }

        //When the player runs out of mistakes, stop the game and display the final score
        if (mistakesLeft <= 0) {
            cardSpawnTimeline.stop();
            gamePane.getChildren().clear();
            scoreLbl.setText((int)score + "!!!");
        }

    }
}
