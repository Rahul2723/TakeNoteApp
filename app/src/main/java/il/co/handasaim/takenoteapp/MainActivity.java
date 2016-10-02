package il.co.handasaim.takenoteapp;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NoteListFragment listFragment; // Shows a list of notes
    EditNoteFragment editFragment; // Shows a text edit for a single note
    ArrayAdapter<Note> noteAdapter; // Adapter for note list
    ArrayList<Note> noteList;
    NotesService notesService; // Object service layer
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = new NoteListFragment();
        editFragment = new EditNoteFragment();

        notesService = ParseNotesServices.getInstance();

        getFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, listFragment).commit();
        initializeList();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note editedNote = notesService.createNote();
                noteAdapter.add(editedNote);
                CallEditFragment(editedNote);
            }
        });
    }

    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }

    @Override
    protected void onDestroy() {
        notesService.saveNotes();
        super.onDestroy();
    }


    /**
     * Presents the note edit fragment for a given note number
     *
     * @param position Note number
     */
    public void CallEditFragment(int position) {
        CallEditFragment(noteAdapter.getItem(position));
    }

    /**
     * Presents the note edit fragment for a given note
     *
     * @param editedNote The note to edit
     */
    private void CallEditFragment(Note editedNote) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", editedNote);
        editFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, editFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Initializes the note list and list adapter as well as
     * fetching the list of notes from the service
     */
    private void initializeList() {
        noteList = new ArrayList<>();
        noteAdapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, noteList);
        listFragment.setListAdapter(noteAdapter);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                notesService.fetchNotes();
                return null;
            }

            @Override
            protected void onPostExecute(Void notes) {
                for (Note note : notesService.getNotes()) {
                    noteAdapter.add(note);
                }
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
