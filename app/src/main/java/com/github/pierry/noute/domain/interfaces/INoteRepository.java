package com.github.pierry.noute.domain.interfaces;

import com.github.pierry.noute.domain.Note;
import java.util.List;

public interface INoteRepository extends IRepositoryBase<Note> {

  List<Note> getByContent(String content);

  List<Note> getByTimestamp(String timestamp);

  List<Note> getByKind(long kind);

  List<Note> getFavs();
}
