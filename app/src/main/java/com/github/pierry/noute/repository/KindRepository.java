package com.github.pierry.noute.repository;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.github.pierry.noute.domain.Kind;
import com.github.pierry.noute.domain.interfaces.IKindRepository;
import java.util.List;
import org.androidannotations.annotations.EBean;

@EBean public class KindRepository implements IKindRepository {
  @Override public List<Kind> get() {
    return new Select().from(Kind.class).execute();
  }

  @Override public Kind getById(long id) {
    return new Select().from(Kind.class).where("Id=?", id).executeSingle();
  }

  @Override public long create(Kind item) {
    return item.save();
  }

  @Override public boolean update(Kind item) {
    return item.save() > 0;
  }

  @Override public boolean delete(long id) {
    try {
      new Delete().from(Kind.class).where("Id=?", id).executeSingle();
      return true;
    } catch (Exception ignored) {
      ignored.printStackTrace();
      return false;
    }
  }
}
