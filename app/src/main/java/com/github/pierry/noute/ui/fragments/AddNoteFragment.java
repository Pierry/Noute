package com.github.pierry.noute.ui.fragments;

import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.services.NoteService;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.UiThread;

public class AddNoteFragment {

  @Bean(NoteService.class) INoteService noteService;

  @AfterViews void init() {

  }

  @Background void addNote() {

  }

  @UiThread void clear() {

  }
}
