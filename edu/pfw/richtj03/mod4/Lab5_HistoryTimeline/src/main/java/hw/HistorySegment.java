package hw;

import javafx.scene.image.Image;

/**
 * Represents a segment of history with a title, description, and image.
 * Already implemented for you.
 */

public class HistorySegment {

    private String title;
    private String description;
    private Image image;

    public HistorySegment(String title, String description, String imageURL) {
        this.title = title;
        this.description = description;
        this.image = new Image(imageURL);
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        //Abbreviate the title
        StringBuilder abbTitle = new StringBuilder();
        String[] titleWords = title.split(" ");
        for (String word : titleWords) {
            abbTitle.append(word.charAt(0));
        }
        return abbTitle.toString();
    }
}
