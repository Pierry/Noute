package com.github.pierry.noute.domain.interfaces;

import com.github.pierry.noute.domain.Note;
import java.util.List;

public interface INoteService {

  List<Note> getAll();

  List<Note> getByContent(String content);

  List<Note> getByTimestamp(String timestamp);

  List<Note> getByKind(long kind);

  void create(Note note);

  void update(Note note);

  void delete(long id);
}
