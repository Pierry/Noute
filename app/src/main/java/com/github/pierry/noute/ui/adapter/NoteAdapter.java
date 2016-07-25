package com.github.pierry.noute.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.pierry.noute.R;
import com.github.pierry.noute.common.DateHelper;
import com.github.pierry.noute.common.FontfaceHelper;
import com.github.pierry.noute.common.TimeNow;
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.simpletoast.SimpleToast;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

  private List<Note> notes;
  private Context context;
  private INoteService noteService;

  public class NoteHolder extends RecyclerView.ViewHolder {
    public TextView content;
    public TextView timestamp;
    public CardView cardView;

    public NoteHolder(View view) {
      super(view);
      content = (TextView) view.findViewById(R.id.content);
      timestamp = (TextView) view.findViewById(R.id.timestamp);
      cardView = (CardView) view.findViewById(R.id.cardView);
      FontfaceHelper.setFontFace(context, content);
      FontfaceHelper.setFontFace(context, timestamp);
    }
  }

  public NoteAdapter(Context context, List<Note> notes, INoteService noteService) {
    this.context = context;
    this.notes = notes;
    this.noteService = noteService;
  }

  @Override public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_note, parent, false);
    return new NoteHolder(itemView);
  }

  @Override public void onBindViewHolder(NoteHolder holder, final int position) {
    Note note = notes.get(position);
    holder.content.setText(note.getContent());
    String date = DateHelper.date(note.getTimestamp());
    holder.timestamp.setText(date);
    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override public boolean onLongClick(View view) {
        alert(position);
        return true;
      }
    });
  }

  void alert(final int position) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
    alertDialog.setTitle(context.getResources().getString(R.string.popup_message));
    alertDialog.setPositiveButton(R.string.favorite, new DialogInterface.OnClickListener() {
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
    });
    alertDialog.setCancelable(true);
    alertDialog.show();
  }

  @Override public int getItemCount() {
    return notes.size();
  }
}
