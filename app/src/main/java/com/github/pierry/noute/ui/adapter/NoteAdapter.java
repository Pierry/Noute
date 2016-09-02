package com.github.pierry.noute.ui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
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

  private FragmentManager fragmentManager;

  public void addItems(List<Note> notes) {
    this.items = notes;
  }

  public void fragmentManagerInject(FragmentManager fragmentManager) {
    this.fragmentManager = fragmentManager;
  }

  @Override protected NoteView onCreateItemView(ViewGroup parent, int viewType) {
    return NoteView_.build(context);
  }

  @Override public void onBindViewHolder(ViewWrapper<NoteView> viewHolder, int position) {
    NoteView view = viewHolder.getView();
    view.fragmentManagerInject(fragmentManager);
    Note note = items.get(position);

    view.bind(note, position);
  }
}
