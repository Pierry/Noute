package com.github.pierry.noute.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.pierry.noute.R;
import com.github.pierry.noute.domain.Note;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

  private List<Note> notes;
  private Context context;

  public class NoteHolder extends RecyclerView.ViewHolder {
    public TextView content;
    public TextView timestamp;

    public NoteHolder(View view) {
      super(view);
      content = (TextView) view.findViewById(R.id.content);
      timestamp = (TextView) view.findViewById(R.id.timestamp);
    }
  }

  public NoteAdapter(Context context, List<Note> notes) {
    this.context = context;
    this.notes = notes;
  }

  @Override public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_note, parent, false);
    return new NoteHolder(itemView);
  }

  @Override public void onBindViewHolder(NoteHolder holder, int position) {
    Note note = notes.get(position);
    holder.content.setText(note.getContent());
    holder.timestamp.setText(note.getTimestamp());
  }

  @Override public int getItemCount() {
    return notes.size();
  }
}
