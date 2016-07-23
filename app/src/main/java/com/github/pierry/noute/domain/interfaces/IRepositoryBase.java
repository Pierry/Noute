package com.github.pierry.noute.domain.interfaces;

import java.util.List;

public interface IRepositoryBase<T> {

  List<T> get();

  T getById(long id);

  long create(T item);

  boolean update(T item);

  boolean delete(long id);
}
