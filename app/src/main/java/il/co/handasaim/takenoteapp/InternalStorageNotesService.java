package il.co.handasaim.takenoteapp;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by Eidan on 9/16/2016.
 */
public class InternalStorageNotesService implements NotesService {

    private final String FILE_PATH = "notes.txt";
    private final String NOTE_DELIMITER = "\n////NOTESEPERATOR////\n";
    private final Context context;
    ArrayList<Note> notes;

    public InternalStorageNotesService(Context context) {
        this.context = context;
        notes = null;
    }

    @Override
    public void fetchNotes() {
        notes = new ArrayList<>();
        File file = new File(context.getFilesDir(), FILE_PATH);
        try {
            file.createNewFile(); // Creates fine if it does not exist
            Scanner s = new Scanner(context.openFileInput(FILE_PATH)); // Using a scanner for smart reading
            s.useDelimiter(NOTE_DELIMITER); // This string delimits the note texts
            while (s.hasNext()) {
                notes.add(new Note(s.next()));
            }
            s.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Error with file", e);
        }
    }

    @Override
    public void saveNotes() {
        try {
            PrintWriter pw = new PrintWriter(context.openFileOutput(FILE_PATH, context.MODE_PRIVATE));
            for(Note note : notes) {
                pw.print(note.toString());
                pw.print(NOTE_DELIMITER); // Delimiter between notes for easier reading
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Iterable<Note> getNotes() {
        return notes;
    } // as simple as that

    @Override
    public Note createNote() {
        Note note = new Note("");
        notes.add(note);
        return note; // returning a new note already in the synced list
    }

}
