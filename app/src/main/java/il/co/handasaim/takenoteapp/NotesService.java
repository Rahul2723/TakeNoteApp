package il.co.handasaim.takenoteapp;

/**
 * Created by Eidan on 9/16/2016.
 */
public interface NotesService {

    /**
     * Returns a list of notes
     * @return a list of notes
     */
    Iterable<Note> getNotes();

    /**
     * Creates a new note in the list
     * @return a new note
     */
    Note createNote();

    /**
     * Fetches the note from an external source
     * must be called prior to other functions
     */
    void fetchNotes();

    /**
     * Saves the notes to the external source
     */
    void saveNotes();
}
