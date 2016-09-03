package com.github.pierry.noute.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SwitchCompat;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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

@EFragment(R.layout.options_fragment) public class OptionsFragment extends DialogFragment
    implements CompoundButton.OnCheckedChangeListener {

  @ViewById TextView custom;
  @ViewById EditText title;
  @ViewById EditText content;
  @ViewById SwitchCompat switchCompat;
  @ViewById TextView favorite;
  @ViewById TextView selectColor;
  @ViewById Button pink;
  @ViewById Button lightpink;
  @ViewById Button orange;
  @ViewById Button yellow;
  @ViewById Button green;
  @ViewById Button lightgreen;
  @ViewById Button blue;
  @ViewById Button gray;
  @ViewById Button black;
  @ViewById Button white;
  @ViewById Button remove;
  @ViewById Button ok;

  @Bean(NoteService.class) INoteService noteService;

  private Note note;

  public static final String PINK_COLOR = "#f7398b";
  public static final String LIGHT_PINK_COLOR = "#ff98c5";
  public static final String ORANGE_COLOR = "#ff9b54";
  public static final String YELLOW_COLOR = "#ffe958";
  public static final String GREEN_COLOR = "#4ef564";
  public static final String LIGHT_GREEN_COLOR = "#96f4a2";
  public static final String BLUE_COLOR = "#75a8fa";
  public static final String GRAY_COLOR = "#b1b1b1";
  public static final String BLACK_COLOR = "#000000";
  public static final String WHITE_COLOR = "#FFFFFF";

  enum colors {
    PINK_COLOR, LIGHT_PINK_COLOR, ORANGE_COLOR, YELLOW_COLOR, GREEN_COLOR,
    LIGHT_GREEN_COLOR, BLUE_COLOR, GRAY_COLOR, BLACK_COLOR, WHITE_COLOR
  }

  public static OptionsFragment_ newInstance(Note note) {
    OptionsFragment_ f = new OptionsFragment_();
    Bundle args = new Bundle();
    args.putParcelable("note", note);
    f.setArguments(args);

    return f;
  }

  @AfterViews void init() {
    getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    switchCompat.setOnCheckedChangeListener(this);
    faces();
    note = (Note) getArguments().get("note");
    title.setText(note.getTitle());
    content.setText(note.getContent());
    if (note.isFav()) {
      switchCompat.setChecked(true);
      return;
    }
    switchCompat.setChecked(false);
  }

  @UiThread void faces() {
    FontfaceHelper.setFontFace(getActivity(), custom);
    FontfaceHelper.setFontFace(getActivity(), favorite);
    FontfaceHelper.setFontFace(getActivity(), selectColor);
    FontfaceHelper.setFontFace(getActivity(), remove);
    FontfaceHelper.setFontFace(getActivity(), ok);
  }

  @Click void pink() {
    note.changeBackground(PINK_COLOR);
    noteService.update(note);
    colors(colors.PINK_COLOR);
  }

  @Click void lightpink() {
    note.changeBackground(LIGHT_PINK_COLOR);
    noteService.update(note);
    colors(colors.LIGHT_PINK_COLOR);
  }

  @Click void orange() {
    note.changeBackground(ORANGE_COLOR);
    noteService.update(note);
    colors(colors.ORANGE_COLOR);
  }

  @Click void yellow() {
    note.changeBackground(YELLOW_COLOR);
    noteService.update(note);
    colors(colors.YELLOW_COLOR);
  }

  @Click void lightgreen() {
    note.changeBackground(LIGHT_GREEN_COLOR);
    noteService.update(note);
    colors(colors.LIGHT_GREEN_COLOR);
  }

  @Click void green() {
    note.changeBackground(GREEN_COLOR);
    noteService.update(note);
    colors(colors.GREEN_COLOR);
  }

  @Click void remove() {
    noteService.delete(note.getId());
    getDialog().dismiss();
    getActivity().recreate();
    SimpleToast.error(getActivity(), getActivity().getResources().getString(R.string.deleted));
  }

  @Click void blue() {
    note.changeBackground(BLUE_COLOR);
    noteService.update(note);
    colors(colors.BLUE_COLOR);
  }

  @Click void gray() {
    note.changeBackground(GRAY_COLOR);
    noteService.update(note);
    colors(colors.GRAY_COLOR);
  }

  @Click void black() {
    note.changeBackground(BLACK_COLOR);
    noteService.update(note);
    colors(colors.BLACK_COLOR);
  }

  @Click void white() {
    note.changeBackground(WHITE_COLOR);
    noteService.update(note);
    colors(colors.WHITE_COLOR);
  }

  @Click void ok() {
    note.changeTitle(title.getText().toString());
    note.changeContent(content.getText().toString());
    noteService.update(note);
    getDialog().dismiss();
    getActivity().recreate();
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
    pink.setAlpha(0.5f);
    lightpink.setAlpha(0.5f);
    orange.setAlpha(0.5f);
    yellow.setAlpha(0.5f);
    green.setAlpha(0.5f);
    lightgreen.setAlpha(0.5f);
    blue.setAlpha(0.5f);
    gray.setAlpha(0.5f);
    black.setAlpha(0.5f);
    white.setAlpha(0.5f);

    switch (colors) {
      case PINK_COLOR:
        pink.setAlpha(1f);
        break;
      case LIGHT_PINK_COLOR:
        lightpink.setAlpha(1f);
        break;
      case ORANGE_COLOR:
        orange.setAlpha(1f);
        break;
      case YELLOW_COLOR:
        yellow.setAlpha(1f);
        break;
      case GREEN_COLOR:
        green.setAlpha(1f);
        break;
      case LIGHT_GREEN_COLOR:
        lightgreen.setAlpha(1f);
        break;
      case BLUE_COLOR:
        blue.setAlpha(1f);
        break;
      case GRAY_COLOR:
        gray.setAlpha(1f);
        break;
      case BLACK_COLOR:
        black.setAlpha(1f);
        break;
      case WHITE_COLOR:
        white.setAlpha(1f);
        break;
    }
  }

  @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    if (note.isFav() == b) {
      return;
    }
    if (b) {
      favorite();
    } else {
      notFavorite();
    }
  }
}
