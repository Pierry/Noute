package com.github.pierry.noute.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.noute.R;
import com.github.pierry.noute.common.DateHelper;
import com.github.pierry.noute.common.FontfaceHelper;
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.ui.fragments.AlertFragment;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.adapter_note) public class NoteView extends RelativeLayout {

  @ViewById TextView noteContent;
  @ViewById TextView title;
  @ViewById TextView timestamp;
  @ViewById CardView cardView;

  private Context context;
  private FragmentManager fragmentManager;
  private Note note;

  public NoteView(Context context) {
    super(context);
    this.context = context;
  }

  @UiThread public void fragmentManagerInject(FragmentManager fm) {
    this.fragmentManager = fm;
  }

  @UiThread public void face() {
    FontfaceHelper.setFontFace(context, title);
    FontfaceHelper.setFontFace(context, noteContent);
    FontfaceHelper.setFontFace(context, timestamp);
  }

  @UiThread public void bind(final Note note, final int position) {
    this.note = note;
    face();
    title.setText(note.getTitle());
    noteContent.setText(note.getContent());
    String date = DateHelper.date(note.getTimestamp());
    timestamp.setText(date);
    String color = "#EAEAEA";
    if (note.getBackgroundColor() != null) {
      color = note.getBackgroundColor();
    }
    cardView.setCardBackgroundColor(Color.parseColor(color));
  }

  @Click void cardView(){
    FragmentTransaction ft = fragmentManager.beginTransaction();
    DialogFragment newFragment = AlertFragment.newInstance(note);
    newFragment.show(fragmentManager, "dialog");
  }
}
