package il.co.handasaim.takenoteapp;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eidan on 9/22/2016.
 */
public class ParseNotesServices implements NotesService {

    private static ParseNotesServices instance = null;

    public static ParseNotesServices getInstance() {
        if(instance == null)
            instance = new ParseNotesServices();
        return instance;
    }

    /**
     * An internal list of notes
     */
    List<Note> notes;

    /**
     * Creates a new instance of the class
     * note that this constructor is private, which allows us to implement a singleton
     */
    private ParseNotesServices() {
        notes = new ArrayList<>();
    }

    @Override
    public Iterable<Note> getNotes() {
        return notes;
    }

    @Override
    public Note createNote() {
        Note note = new Note("");
        notes.add(note);
        return note;
    }

    @Override
    public void fetchNotes() {
        ParseQuery<Note> query = new ParseQuery<>("Note");
        try {
            notes = query.find();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNotes() {
        for(ParseObject note : notes)
        {
            note.saveInBackground();
        }
    }
}
