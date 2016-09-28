package com.github.pierry.noute.ui.fragments;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
import com.github.pierry.noute.common.MyPrefs_;
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.services.NoteService;
import com.github.pierry.noute.ui.adapter.NoteAdapter;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.note_fragment) public class NoteFragment extends Fragment {

  @ViewById RotateLoading rotateLoading;
  @ViewById RotateLoading rotateLoadingBottom;
  @ViewById RelativeLayout body;
  @ViewById RecyclerView recyclerView;
  @ViewById EditText content;
  @ViewById Button add;
  @ViewById SwipeRefreshLayout swipeRefreshLayout;

  @Bean(NoteService.class) INoteService noteService;
  @Bean(NoteAdapter.class) NoteAdapter noteAdapter;

  @Pref MyPrefs_ myPrefs;

  public static String BY_DATE = "bydate";
  public static String BY_COLOR = "bycolor";
  public static String BY_ALPHABETICAL = "byalphabetical";

  private static final int FIRST_PAGE = 0;
  public static String GRID = "grid";
  public static String LINEAR = "linear";

  private List<Note> notes = new ArrayList<>();
  private int page = 0;
  private int lastVisibleItem;

  private LinearLayoutManager linearManager;
  private GridLayoutManager gridManager;
  private InfiniteScrollListener infiniteScroll;

  @AfterViews void init() {
    setHasOptionsMenu(true);
    faces();
    showLoader();
    loadView();
    noteAdapter.fragmentManagerInject(getActivity().getSupportFragmentManager());
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
    Note note = null;
    if (!contentText.contains("\n\n")) {
      note = new Note(contentText, "");
    } else {
      String[] splited = contentText.split("\n\n");
      String title = splited[0];
      String body = "";
      for (String item : splited) {
        body.concat(item + "\n\n");
      }
      note = new Note(title, body);
    }
    note.changeBackground(OptionsFragment.WHITE_COLOR);
    noteService.create(note);
    notes.add(0, note);
    noteAdapter.notifyDataSetChanged();
    content.setText("");
  }

  @UiThread void gridView() {
    gridManager = new GridLayoutManager(getActivity(), 2);
    gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        return 1;
      }
    });
    recyclerView.setLayoutManager(gridManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    load();
  }

  @UiThread void linearView() {
    linearManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(linearManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    load();
  }

  void load() {
    if (myPrefs.organize().get().equals(BY_DATE)) {
      loadByDate();
    } else if (myPrefs.organize().get().equals(BY_ALPHABETICAL)) {
      loadByAlphabetical();
    } else {
      loadByColor();
    }
  }

  void loadView() {
    if (myPrefs.view().get().equals("linear")) {
      linearView();
      createInfiniteScrollListener();
    } else {
      gridView();
    }
  }

  @Background public void loadByColor() {
    notes = noteService.getByColor(FIRST_PAGE);
    hideLoader();
    adapter();
  }

  @Background public void loadByDate() {
    notes = noteService.getByDatetime(FIRST_PAGE);
    hideLoader();
    adapter();
  }

  @Background public void loadByAlphabetical() {
    notes = noteService.getByAlphabetical(FIRST_PAGE);
    hideLoader();
    adapter();
  }

  @UiThread void adapter() {
    noteAdapter.addItems(notes);
    recyclerView.setAdapter(noteAdapter);
    //recyclerView.addOnScrollListener(infiniteScroll);
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

  @UiThread void createInfiniteScrollListener() {
    final Context context = getActivity();
    infiniteScroll = new InfiniteScrollListener(20, linearManager) {
      @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
        if (firstVisibleItemPosition == lastVisibleItem) {
          return;
        }
        lastVisibleItem = firstVisibleItemPosition;
        page++;
        rotateLoadingBottom.animate();
        rotateLoadingBottom.setVisibility(View.VISIBLE);
        refreshView(recyclerView, noteAdapter, firstVisibleItemPosition);
      }
    };
  }

  @UiThread void searchAndUpdate(String query) {
    notes = noteService.getByContent(query);
    hideLoader();
    adapter();
  }

  @OptionsItem(R.id.actionOrganize) void organize() {
    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
    DialogFragment newFragment = OrganizeFragment_.newInstance();
    newFragment.show(getActivity().getSupportFragmentManager(), "organize");
  }

  @OptionsItem(R.id.actionView) void view() {
    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
    DialogFragment newFragment = ViewFragment_.newInstance();
    newFragment.show(getActivity().getSupportFragmentManager(), "view");
  }

  @Override public void onDestroyView() {
    if (add.getVisibility() == View.VISIBLE) {
      add();
    }
    super.onDestroyView();
  }
}
