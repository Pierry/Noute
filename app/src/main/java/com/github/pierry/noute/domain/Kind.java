package com.github.pierry.noute.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Kind") public class Kind extends Model {

  @Column(name = "Title") String title;

  public Kind() {
    super();
  }

  public Kind(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
