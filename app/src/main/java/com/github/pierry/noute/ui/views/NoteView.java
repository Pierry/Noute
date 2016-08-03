package com.github.pierry.noute.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.noute.R;
import com.github.pierry.noute.common.DateHelper;
import com.github.pierry.noute.common.FontfaceHelper;
import com.github.pierry.noute.domain.Note;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.adapter_note) public class NoteView extends RelativeLayout {

  @ViewById TextView content;
  @ViewById TextView title;
  @ViewById TextView timestamp;
  @ViewById CardView cardView;

  private Context context;

  public NoteView(Context context) {
    super(context);
    this.context = context;
  }

  @UiThread public void face() {
    FontfaceHelper.setFontFace(context, title);
    FontfaceHelper.setFontFace(context, content);
    FontfaceHelper.setFontFace(context, timestamp);
  }

  @UiThread public void bind(Note note, final int position) {
    face();
    title.setText(note.getTitle());
    content.setText(note.getContent());
    String date = DateHelper.date(note.getTimestamp());
    timestamp.setText(date);
    this.setOnLongClickListener(new View.OnLongClickListener() {
      @Override public boolean onLongClick(View view) {
        alert(position);
        return true;
      }
    });
    String color = "#EAEAEA";
    if (note.getBackgroundColor() != null) {
      color = note.getBackgroundColor();
    }
    cardView.setCardBackgroundColor(Color.parseColor(color));
  }

  @UiThread void alert(final int position) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.card_actions, null);
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
    alertDialog.setTitle(context.getResources().getString(R.string.popup_message));
    /*alertDialog.setPositiveButton(R.string.favorite, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        Note note = notes.get(position);
        note.favorite();
        noteService.update(note);
        SimpleToast.ok(context, context.getResources().getString(R.string.added_to_fav));
      }
    });
    alertDialog.setNeutralButton(R.string.cancel, null);
    alertDialog.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        Note note = notes.get(position);
        noteService.delete(note.getId());
        notes.remove(note);
        SimpleToast.error(context, context.getResources().getString(R.string.deleted));
        notifyDataSetChanged();
      }
    });*/
    alertDialog.setView(view);
    alertDialog.setCancelable(true);
    alertDialog.show();
  }
}
