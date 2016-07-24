package com.github.pierry.noute.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.threeten.bp.Instant;

@Table(name = "Note") public class Note extends Model {

  @Column(name = "Content") String content;
  @Column(name = "Timestamp") String timestamp;
  @Column(name = "BackgroundColor") String backgroundColor;
  @Column(name = "Kind") Kind kind;
  @Column(name = "IsFav") boolean isFav;

  public Note() {
    super();
  }

  public Note(String content) {
    this.content = content;
    this.timestamp = Instant.now().toString();
    this.kind = null;
    this.isFav = false;
    this.backgroundColor = null;
  }

  public void favorite() {
    this.isFav = true;
  }

  public void notFavorite() {
    this.isFav = false;
  }

  public void changeBackground(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public void changeKind(Kind kind) {
    this.kind = kind;
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
