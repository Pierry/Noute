package com.github.pierry.noute.ui.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SwitchCompat;
import android.widget.Button;
import android.widget.CompoundButton;
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
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.card_actions) public class AlertFragment extends DialogFragment
    implements CompoundButton.OnCheckedChangeListener {

  @ViewById TextView custom;
  @ViewById SwitchCompat switchCompat;
  @ViewById TextView favorite;
  @ViewById TextView selectColor;
  @ViewById TextView actions;
  @ViewById Button red;
  @ViewById Button white;
  @ViewById Button blue;
  @ViewById Button yellow;
  @ViewById Button silver;
  @ViewById Button remove;
  @ViewById Button ok;

  @Bean(NoteService.class) INoteService noteService;

  private Note note;

  private static final int REQUEST_CODE = 1;

  enum colors {
    RED, BLUE, YELLOW, SILVER, WHITE
  }

  public static AlertFragment_ newInstance(Note note) {
    AlertFragment_ f = new AlertFragment_();
    Bundle args = new Bundle();
    args.putParcelable("note", note);
    f.setArguments(args);

    return f;
  }

  @AfterViews void init() {
    getDialog().setTitle(R.string.dialog_title);
    switchCompat.setOnCheckedChangeListener(this);
    faces();
    note = (Note) getArguments().get("note");
  }

  @UiThread void faces() {
    FontfaceHelper.setFontFace(getActivity(), custom);
    FontfaceHelper.setFontFace(getActivity(), favorite);
    FontfaceHelper.setFontFace(getActivity(), selectColor);
    FontfaceHelper.setFontFace(getActivity(), actions);
    FontfaceHelper.setFontFace(getActivity(), remove);
    FontfaceHelper.setFontFace(getActivity(), ok);
  }

  @Click void red() {
    note.changeBackground("#fdc0c0");
    noteService.update(note);
    colors(colors.RED);
  }

  @Click void blue() {
    note.changeBackground("#d7eafc");
    noteService.update(note);
    colors(colors.BLUE);
  }

  @Click void yellow() {
    note.changeBackground("#ecec9b");
    noteService.update(note);
    colors(colors.YELLOW);
  }

  @Click void silver() {
    note.changeBackground("#c2c1c1");
    noteService.update(note);
    colors(colors.SILVER);
  }

  @Click void white() {
    note.changeBackground("#ffffff");
    noteService.update(note);
    colors(colors.WHITE);
  }

  @Click void remove() {
    noteService.delete(note.getId());
    SimpleToast.error(getActivity(), getActivity().getResources().getString(R.string.deleted));
  }

  @Click void ok() {
    getDialog().dismiss();
  }

  @UiThread void favorite() {
    note.favorite();
    noteService.update(note);

    SimpleToast.ok(getActivity(), getActivity().getResources().getString(R.string.added_to_fav));
  }

  @UiThread void notFavorite() {
    note.notFavorite();
    noteService.update(note);

    SimpleToast.ok(getActivity(),
        getActivity().getResources().getString(R.string.removed_from_fav));
  }

  @UiThread void colors(colors colors) {
    red.setBackgroundColor(Color.parseColor("#fdc0c0"));
    blue.setBackgroundColor(Color.parseColor("#d7eafc"));
    yellow.setBackgroundColor(Color.parseColor("#ffffd8"));
    silver.setBackgroundColor(Color.parseColor("#EAEAEA"));
    white.setBackgroundColor(Color.parseColor("#ffffff"));
    switch (colors) {
      case RED:
        red.setBackgroundColor(Color.parseColor("#f48f8f"));
        break;
      case BLUE:
        blue.setBackgroundColor(Color.parseColor("#9dc7ef"));
        break;
      case YELLOW:
        yellow.setBackgroundColor(Color.parseColor("#f2f28d"));
        break;
      case SILVER:
        silver.setBackgroundColor(Color.parseColor("#d2d2d2"));
        break;
      case WHITE:
        white.setBackgroundColor(Color.parseColor("#eaeaea"));
        break;
    }
  }

  @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    if (b) {
      favorite();
    } else {
      notFavorite();
    }
  }
}
