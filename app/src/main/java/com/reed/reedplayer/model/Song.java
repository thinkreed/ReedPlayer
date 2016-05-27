package com.reed.reedplayer.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thinkreed on 16/5/27.
 */
public class Song implements Parcelable {
  public String path;
  public String artist;
  public Uri cover;
  public String title;
  public String duration;
  public String id;

  @Override
  public String toString() {
    return "Song{" +
        "path='" + path + '\'' +
        ", artist='" + artist + '\'' +
        ", cover=" + cover +
        ", title='" + title + '\'' +
        ", duration='" + duration + '\'' +
        ", id='" + id + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Song song = (Song) o;

    if (path != null ? !path.equals(song.path) : song.path != null) return false;
    if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
    if (cover != null ? !cover.equals(song.cover) : song.cover != null) return false;
    if (title != null ? !title.equals(song.title) : song.title != null) return false;
    if (duration != null ? !duration.equals(song.duration) : song.duration != null) return false;
    return id != null ? id.equals(song.id) : song.id == null;

  }

  @Override
  public int hashCode() {
    int result = path != null ? path.hashCode() : 0;
    result = 31 * result + (artist != null ? artist.hashCode() : 0);
    result = 31 * result + (cover != null ? cover.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (duration != null ? duration.hashCode() : 0);
    result = 31 * result + (id != null ? id.hashCode() : 0);
    return result;
  }

  private Song(Builder builder) {
    path = builder.path;
    artist = builder.artist;
    cover = builder.cover;
    title = builder.title;
    duration = builder.duration;
    id = builder.id;
  }

  public static final class Builder {
    private String path;
    private String artist;
    private Uri cover;
    private String title;
    private String duration;
    private String id;

    public Builder() {}

    public Builder(Song copy) {
      this.path = copy.path;
      this.artist = copy.artist;
      this.cover = copy.cover;
      this.title = copy.title;
      this.duration = copy.duration;
      this.id = copy.id;
    }

    public Builder path(String val) {
      path = val;
      return this;
    }

    public Builder artist(String val) {
      artist = val;
      return this;
    }

    public Builder cover(Uri val) {
      cover = val;
      return this;
    }

    public Builder title(String val) {
      title = val;
      return this;
    }

    public Builder duration(String val) {
      duration = val;
      return this;
    }

    public Builder id(String val) {
      id = val;
      return this;
    }

    public Song build() {
      return new Song(this);
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.path);
    dest.writeString(this.artist);
    dest.writeParcelable(this.cover, flags);
    dest.writeString(this.title);
    dest.writeString(this.duration);
    dest.writeString(this.id);
  }

  protected Song(Parcel in) {
    this.path = in.readString();
    this.artist = in.readString();
    this.cover = in.readParcelable(Uri.class.getClassLoader());
    this.title = in.readString();
    this.duration = in.readString();
    this.id = in.readString();
  }

  public static final Creator<Song> CREATOR = new Creator<Song>() {
    @Override
    public Song createFromParcel(Parcel source) {
      return new Song(source);
    }

    @Override
    public Song[] newArray(int size) {
      return new Song[size];
    }
  };
}
