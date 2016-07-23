package com.github.pierry.noute.repository;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.github.pierry.noute.domain.Note;
import com.github.pierry.noute.domain.interfaces.INoteRepository;
import java.util.List;
import org.androidannotations.annotations.EBean;

@EBean public class NoteRepository implements INoteRepository {
  @Override public List<Note> get() {
    return new Select().from(Note.class).orderBy("Timestamp DESC").execute();
  }

  @Override public Note getById(long id) {
    return new Select().from(Note.class).where("Id=?", id).executeSingle();
  }

  @Override public long create(Note item) {
    return item.save();
  }

  @Override public boolean update(Note item) {
    return item.save() > 0;
  }

  @Override public boolean delete(long id) {
    try {
      new Delete().from(Note.class).where("Id=?", id).executeSingle();
      return true;
    } catch (Exception ignored) {
      ignored.printStackTrace();
      return false;
    }
  }

  @Override public List<Note> getByContent(String content) {
    return new Select().from(Note.class).
        where("Content like '%" + content + "%'").orderBy("Content DESC").execute();
  }

  @Override public List<Note> getByTimestamp(String timestamp) {
    return new Select().from(Note.class).
        where("Timestamp = '" + timestamp + "'").execute();
  }

  @Override public List<Note> getByKind(long id) {
    return new Select().from(Note.class).where("Kind=?", id).orderBy("Content DESC").execute();
  }
}
