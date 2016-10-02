package il.co.handasaim.takenoteapp;

import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Eidan on 9/16/2016.
 */
public class NoteListFragment extends ListFragment {

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MainActivity activity = (MainActivity)getActivity();
        activity.CallEditFragment(position);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getFloatingActionButton().show();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).getFloatingActionButton().hide();
    }
}
