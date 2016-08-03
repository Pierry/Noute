package com.github.pierry.noute.ui.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.services.NoteService;
import com.github.pierry.noute.ui.common.RecyclerViewAdapterBase;
import com.github.pierry.noute.ui.common.ViewWrapper;
import com.github.pierry.noute.ui.views.NoteView;
import com.github.pierry.noute.ui.views.NoteView_;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean public class NoteAdapter extends RecyclerViewAdapterBase<Note, NoteView> {

  @RootContext Context context;

  @Bean(NoteService.class) INoteService noteService;

  public void addItems(List<Note> notes) {
    this.items = notes;
  }

  @Override protected NoteView onCreateItemView(ViewGroup parent, int viewType) {
    return NoteView_.build(context);
  }

  @Override public void onBindViewHolder(ViewWrapper<NoteView> viewHolder, int position) {
    NoteView view = viewHolder.getView();
    Note note = items.get(position);

    view.bind(note, position);
  }
}
