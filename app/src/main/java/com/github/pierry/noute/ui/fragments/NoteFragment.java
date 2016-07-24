package com.github.pierry.noute.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.notes_fragment) public class NoteFragment extends Fragment {

  @ViewById TextView empty;
  @ViewById RotateLoading rotateLoading;
  @ViewById RelativeLayout body;
  @ViewById RecyclerView recyclerView;

  @Bean(NoteService.class) INoteService noteService;

  List<Note> notes = new ArrayList<>();
  NoteAdapter noteAdapter;

  @AfterViews void init() {
    showLoader();
    load();
  }

  @UiThread void recyclerViewConfig() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    load();
  }

  @Background void load() {
    notes = noteService.getAll();
    visibleOrNot();
    adapter();
  }

  @UiThread void visibleOrNot() {
    if (notes == null || notes.size() == 0) {
      empty.setVisibility(View.VISIBLE);
    } else {
      empty.setVisibility(View.INVISIBLE);
    }
  }

  @UiThread void adapter() {
    NoteAdapter playerAdapter = new NoteAdapter(getActivity(), notes);
    recyclerView.setAdapter(playerAdapter);
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
