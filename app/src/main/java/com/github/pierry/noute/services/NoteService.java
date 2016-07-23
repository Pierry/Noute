package com.github.pierry.noute.services;

import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteRepository;
import com.github.pierry.noute.domain.interfaces.INoteService;
import com.github.pierry.noute.repository.NoteRepository;
import java.util.Collections;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean public class NoteService implements INoteService {

  @Bean(NoteRepository.class) INoteRepository noteRepository;

  @Override public List<Note> getAll() {
    try {
      return noteRepository.get();
    } catch (Exception e){
      return Collections.emptyList();
    }
  }

  @Override public List<Note> getByContent(String content) {
    try {
      return noteRepository.get();
    } catch (Exception e){
      return Collections.emptyList();
    }
  }

  @Override public List<Note> getByTimestamp(String timestamp) {
    try {
      return noteRepository.getByTimestamp(timestamp);
    } catch (Exception e){
      return Collections.emptyList();
    }
  }

  @Override public List<Note> getByKind(long kind) {
    try {
      return noteRepository.getByKind(kind);
    } catch (Exception e){
      return Collections.emptyList();
    }
  }

  @Override public void create(Note note) {
    try {
      noteRepository.create(note);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public void update(Note note) {
    try {
      noteRepository.update(note);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public void delete(long id) {
    try {
      noteRepository.delete(id);
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}
