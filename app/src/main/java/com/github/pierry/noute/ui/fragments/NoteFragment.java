package com.github.pierry.noute.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.fitloader.RotateLoading;
import com.github.pierry.noute.MainActivity;
import com.github.pierry.noute.R;
import com.github.pierry.noute.common.FontfaceHelper;
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
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.note_fragment) public class NoteFragment extends Fragment {

  @ViewById RotateLoading rotateLoading;
  @ViewById RelativeLayout body;
  @ViewById RecyclerView recyclerView;
  @ViewById EditText content;
  @ViewById Button add;
  @ViewById SwipeRefreshLayout swipeRefreshLayout;

  @Bean(NoteService.class) INoteService noteService;
  @Bean(NoteAdapter.class) NoteAdapter noteAdapter;

  private List<Note> notes = new ArrayList<>();

  @AfterViews void init() {
    setHasOptionsMenu(true);
    faces();
    showLoader();
    recyclerViewConfig();
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        load();
        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }

  @UiThread void faces() {
    FontfaceHelper.setFontFace(getActivity(), content);
    FontfaceHelper.setFontFace(getActivity(), add);
  }

  @AfterTextChange(R.id.content) void content(Editable text, TextView content) {
    if (text.length() > 0) {
      add.setVisibility(View.VISIBLE);
    } else {
      add.setVisibility(View.GONE);
    }
  }

  @Click void add() {
    String contentText = content.getText().toString();
    String[] splited = contentText.split("\n\n");
    Note note = new Note(splited[0], splited[1]);
    noteService.create(note);
    notes.add(0, note);
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
    noteAdapter.addItems(notes);
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

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
    inflater.inflate(R.menu.main_menu, menu);
    MenuItem item = menu.findItem(R.id.actionSearch);
    SearchView searchView =
        new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
    MenuItemCompat.setShowAsAction(item,
        MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
    MenuItemCompat.setActionView(item, searchView);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        showLoader();
        searchAndUpdate(query);
        return true;
      }

      @Override public boolean onQueryTextChange(String newText) {
        if (newText.length() < 3 && newText.length() > 0) {
          return false;
        }
        showLoader();
        searchAndUpdate(newText);
        return true;
      }
    });
    searchView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });
  }

  @UiThread void searchAndUpdate(String query) {
    notes = noteService.getByContent(query);
    hideLoader();
    adapter();
  }
}
