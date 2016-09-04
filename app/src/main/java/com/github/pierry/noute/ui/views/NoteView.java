package com.github.pierry.noute.ui.views;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.noute.MainActivity_;
import com.github.pierry.noute.R;
import com.github.pierry.noute.common.DateHelper;
import com.github.pierry.noute.common.FontfaceHelper;
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.services.NoteService;
import com.github.pierry.noute.ui.fragments.OptionsFragment;
import com.github.pierry.simpletoast.SimpleToast;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.note_adapter) public class NoteView extends RelativeLayout {

  @ViewById TextView noteContent;
  @ViewById TextView title;
  @ViewById TextView timestamp;
  @ViewById CardView cardView;

  @Bean(NoteService.class) INoteService noteService;

  private Context context;
  private FragmentManager fragmentManager;
  private Note note;

  public NoteView(Context context) {
    super(context);
    this.context = context;
  }

  @LongClick(R.id.cardView) void longClickCardView() {
    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setTitle(context.getString(R.string.delete_confirm));
    alert.setPositiveButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        noteService.delete(note.getId());
        ((MainActivity_) context).recreate();
        SimpleToast.error(context, context.getResources().getString(R.string.deleted));
      }
    });
    alert.setNegativeButton(context.getString(R.string.no), null);
    alert.setCancelable(true);
    alert.show();
  }

  @Click void cardView() {
    FragmentTransaction ft = fragmentManager.beginTransaction();
    DialogFragment newFragment = OptionsFragment.newInstance(note);
    newFragment.show(fragmentManager, "dialog");
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
      switch (color) {
        case OptionsFragment.PINK_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_white));
          noteContent.setTextColor(getResources().getColor(R.color.nt_white));
          timestamp.setTextColor(getResources().getColor(R.color.nt_white));
          break;
        case OptionsFragment.LIGHT_PINK_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_black));
          noteContent.setTextColor(getResources().getColor(R.color.nt_black));
          timestamp.setTextColor(getResources().getColor(R.color.nt_black));
          break;
        case OptionsFragment.ORANGE_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_black));
          noteContent.setTextColor(getResources().getColor(R.color.nt_black));
          timestamp.setTextColor(getResources().getColor(R.color.nt_black));
          break;
        case OptionsFragment.YELLOW_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_black));
          noteContent.setTextColor(getResources().getColor(R.color.nt_black));
          timestamp.setTextColor(getResources().getColor(R.color.nt_black));
          break;
        case OptionsFragment.GREEN_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_black));
          noteContent.setTextColor(getResources().getColor(R.color.nt_black));
          timestamp.setTextColor(getResources().getColor(R.color.nt_black));
          break;
        case OptionsFragment.LIGHT_GREEN_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_black));
          noteContent.setTextColor(getResources().getColor(R.color.nt_black));
          timestamp.setTextColor(getResources().getColor(R.color.nt_black));
          break;
        case OptionsFragment.BLUE_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_white));
          noteContent.setTextColor(getResources().getColor(R.color.nt_white));
          timestamp.setTextColor(getResources().getColor(R.color.nt_white));
          break;
        case OptionsFragment.GRAY_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_black));
          noteContent.setTextColor(getResources().getColor(R.color.nt_black));
          timestamp.setTextColor(getResources().getColor(R.color.nt_black));
          break;
        case OptionsFragment.BLACK_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_white));
          noteContent.setTextColor(getResources().getColor(R.color.nt_white));
          timestamp.setTextColor(getResources().getColor(R.color.nt_white));
          break;
        case OptionsFragment.WHITE_COLOR:
          title.setTextColor(getResources().getColor(R.color.nt_black));
          noteContent.setTextColor(getResources().getColor(R.color.nt_black));
          timestamp.setTextColor(getResources().getColor(R.color.nt_black));
          break;
      }
    }
    cardView.setCardBackgroundColor(Color.parseColor(color));
  }
}
