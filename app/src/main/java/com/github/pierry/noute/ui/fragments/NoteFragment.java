package com.github.pierry.noute.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.fitloader.RotateLoading;
import com.github.pierry.noute.R;
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.services.NoteService;
import com.github.pierry.noute.ui.adapter.NoteAdapter;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.note_fragment) public class NoteFragment extends Fragment {

  @ViewById RotateLoading rotateLoading;
  @ViewById RelativeLayout body;
  @ViewById RecyclerView recyclerView;
  @ViewById EditText content;
  @ViewById Button add;

  @Bean(NoteService.class) INoteService noteService;

  List<Note> notes = new ArrayList<>();
  NoteAdapter noteAdapter;

  @AfterViews void init()  {
    showLoader();
    recyclerViewConfig();
  }

  @AfterTextChange(R.id.content) void content(Editable text, TextView content) {
    if (text.length() > 0){
      add.setVisibility(View.VISIBLE);
    } else {
      add.setVisibility(View.GONE);
    }
  }

  @Click void add() {
    String contentText = content.getText().toString();
    Note note = new Note(contentText);
    noteService.create(note);
    notes.add(note);
    noteAdapter.notifyDataSetChanged();
    content.setText("");
  }

  @UiThread void recyclerViewConfig() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    load();
  }

  @Background void load() {
    notes = noteService.getAll();
    hideLoader();
    adapter();
  }

  @UiThread void adapter() {
    noteAdapter = new NoteAdapter(getActivity(), notes);
    recyclerView.setAdapter(noteAdapter);
  }

  @UiThread void showLoader() {
    rotateLoading.start(false);
    rotateLoading.setVisibility(View.VISIBLE);
    body.setVisibility(View.GONE);
  }

  @UiThread void hideLoader() {
    rotateLoading.stop();
    rotateLoading.setVisibility(View.GONE);
    body.setVisibility(View.VISIBLE);
  }
}
