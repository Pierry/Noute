package com.github.pierry.noute.services;

import com.github.pierry.noute.domain.Kind;
import com.github.pierry.noute.domain.interfaces.IKindRepository;
import com.github.pierry.noute.domain.interfaces.IKindService;
import com.github.pierry.noute.repository.KindRepository;
import java.util.Collections;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean public class KindService implements IKindService {

  @Bean(KindRepository.class) IKindRepository kindRepository;

  @Override public List<Kind> get() {
    try {
      return kindRepository.get();
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  @Override public void create(Kind kind) {
    try {
      kindRepository.create(kind);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public void delete(long id) {
    try {
      kindRepository.delete(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
