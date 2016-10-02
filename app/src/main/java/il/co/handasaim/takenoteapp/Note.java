package il.co.handasaim.takenoteapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Represents a single note with text
 * Created by Eidan on 9/16/2016.
 */
@ParseClassName("Note")
public class Note extends ParseObject implements Serializable {

    /**
     * Creates a new instance of the note class
     */
    public Note() {
        super();
    }

    /**
     * Creates a new instance of the note class
     * @param text The note text
     */
    public Note(String text) {
        super();
        setText(text);
    }

    /**
     * Gets the text of the note
     * @return The note text
     */
    @Override
    public String toString() {
        return getString("text");
    }

    /**
     * Sets the text of the note
     * @param text The new text
     */
    public void setText(String text) {
        put("text", text);
    }
}
