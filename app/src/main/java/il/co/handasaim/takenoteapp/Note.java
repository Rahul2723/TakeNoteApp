package il.co.handasaim.takenoteapp;

import java.io.Serializable;

/**
 * Represents a single note with text
 * Created by Eidan on 9/16/2016.
 */
public class Note implements Serializable{

    private String text;

    /**
     * Creates a new instance of the note class
     * @param text The note text
     */
    public Note(String text) {
        this.text = text;
    }

    /**
     * Gets the text of the note
     * @return The note text
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Sets the text of the note
     * @param text The new text
     */
    public void setText(String text) {
        this.text = text;
    }
}
