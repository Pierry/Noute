package com.github.pierry.noute.domain;

import android.os.Parcel;
import android.os.Parcelable;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.threeten.bp.Instant;

@Table(name = "Note") public class Note extends Model implements Parcelable {

  @Column(name = "Title") String title;
  @Column(name = "Content") String content;
  @Column(name = "Timestamp") String timestamp;
  @Column(name = "BackgroundColor") String backgroundColor;
  @Column(name = "Kind") Kind kind;
  @Column(name = "IsFav") boolean isFav;

  public Note() {
    super();
  }

  public Note(String title, String content) {
    this.title = title;
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

  public String getTitle() {
    return title;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(i);
  }

  protected Note(Parcel in) {
    title = in.readString();
    content = in.readString();
    timestamp = in.readString();
    backgroundColor = in.readString();
    isFav = in.readByte() != 0;
  }

  public static final Creator<Note> CREATOR = new Creator<Note>() {
    @Override public Note createFromParcel(Parcel in) {
      return new Note(in);
    }

    @Override public Note[] newArray(int size) {
      return new Note[size];
    }
  };
}
