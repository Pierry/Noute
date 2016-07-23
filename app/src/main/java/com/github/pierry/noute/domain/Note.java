package com.github.pierry.noute.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.threeten.bp.Instant;

@Table(name = "Note") public class Note extends Model {

  @Column(name = "Title") String title;
  @Column(name = "Content") String content;
  @Column(name = "Timestamp") String timestamp;
  @Column(name = "BackgroundColor") String backgroundColor;
  @Column(name = "Kind") Kind kind;

  public Note() {
    super();
  }

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
    this.timestamp = Instant.now().toString();
  }

  public void changeBackground(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public void changeKind(Kind kind) {
    this.kind = kind;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }
}
