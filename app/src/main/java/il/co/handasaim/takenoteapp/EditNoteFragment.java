package il.co.handasaim.takenoteapp;

import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class EditNoteFragment extends android.app.Fragment {

    private Note note;

    public EditNoteFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        note = (Note)getArguments().getSerializable("note");
        EditText editText = (EditText)getView().findViewById(R.id.editText);
        editText.setText(note.toString());
        editText.requestFocus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        EditText editText = (EditText)view.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                note.setText(s.toString());
            }
        });
        return view;
    }
}
