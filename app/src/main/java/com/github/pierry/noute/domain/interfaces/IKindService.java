package com.github.pierry.noute.domain.interfaces;

import com.github.pierry.noute.domain.Kind;
import java.util.List;

public interface IKindService {

  List<Kind> get();

  void create(Kind kind);

  void delete(long id);
}
