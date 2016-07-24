package com.github.pierry.noute.ui.fragments;

import android.support.v4.app.Fragment;
import com.github.pierry.noute.R;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.services.NoteService;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

@EFragment(R.layout.notes_fragment) public class AddNoteFragment extends Fragment {

  @AfterViews void init() {

  }

  @Background void addNote() {

  }

  @UiThread void clear() {

  }
}
