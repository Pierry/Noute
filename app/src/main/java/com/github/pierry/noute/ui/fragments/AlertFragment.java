package com.github.pierry.noute.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.pierry.noute.R;
import com.github.pierry.noute.common.FontfaceHelper;
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.services.NoteService;
import com.github.pierry.simpletoast.SimpleToast;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.card_actions) public class AlertFragment extends DialogFragment {

  @ViewById TextView custom;
  @ViewById SwitchCompat switchCompat;
  @ViewById TextView favorite;
  @ViewById TextView selectColor;
  @ViewById TextView actions;
  @ViewById View red;
  @ViewById View blue;
  @ViewById View yellow;
  @ViewById View silver;
  @ViewById Button remove;
  @ViewById Button cancel;

  @Bean(NoteService.class) INoteService noteService;

  private Note note;

  @AfterViews void init() {
    faces();
    note = (Note) getArguments().get("note");
  }

  public static AlertFragment_ newInstance(Note note) {
    AlertFragment_ f = new AlertFragment_();
    Bundle args = new Bundle();
    args.putParcelable("note", note);
    f.setArguments(args);

    return f;
  }

  @UiThread void faces() {
    FontfaceHelper.setFontFace(getActivity(), custom);
    FontfaceHelper.setFontFace(getActivity(), favorite);
    FontfaceHelper.setFontFace(getActivity(), selectColor);
    FontfaceHelper.setFontFace(getActivity(), actions);
  }

  @Click void remove() {
    note.favorite();
    noteService.update(note);
    SimpleToast.ok(getActivity(), getActivity().getResources().getString(R.string.added_to_fav));
  }

  @Click void cancel() {

  }

  void favorite() {
    noteService.delete(note.getId());
    //notes.remove(note);
    SimpleToast.error(getActivity(), getActivity().getResources().getString(R.string.deleted));
    //notifyDataSetChanged();
  }
}
