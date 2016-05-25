package com.reed.reedplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thinkreed on 16/5/4.
 */
public class Model implements Parcelable {
  public String title;
  public Templete templete;
  public String path;
  public String artist;
  public String cover;
  public Motion motion;

  @Override
  public String toString() {
    return "Model{" +
        "title='" + title + '\'' +
        ", templete=" + templete +
        ", path='" + path + '\'' +
        ", artist='" + artist + '\'' +
        ", cover='" + cover + '\'' +
        ", motion=" + motion +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Model model = (Model) o;

    if (title != null ? !title.equals(model.title) : model.title != null) return false;
    if (templete != model.templete) return false;
    if (path != null ? !path.equals(model.path) : model.path != null) return false;
    if (artist != null ? !artist.equals(model.artist) : model.artist != null) return false;
    if (cover != null ? !cover.equals(model.cover) : model.cover != null) return false;
    return motion != null ? motion.equals(model.motion) : model.motion == null;

  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (templete != null ? templete.hashCode() : 0);
    result = 31 * result + (path != null ? path.hashCode() : 0);
    result = 31 * result + (artist != null ? artist.hashCode() : 0);
    result = 31 * result + (cover != null ? cover.hashCode() : 0);
    result = 31 * result + (motion != null ? motion.hashCode() : 0);
    return result;
  }

  private Model(Builder builder) {
    title = builder.title;
    templete = builder.templete;
    path = builder.path;
    artist = builder.artist;
    cover = builder.cover;
    motion = builder.motion;
  }

  public static final class Builder {
    String title;
    Templete templete;
    String path;
    String artist;
    String cover;
    private Motion motion;

    public Builder() {}

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder templete(Templete templete) {
      this.templete = templete;
      return this;
    }

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public Builder artist(String artist) {
      this.artist = artist;
      return this;
    }

    public Builder cover(String cover) {
      this.cover = cover;
      return this;
    }

    public Builder motion(Motion val) {
      motion = val;
      return this;
    }

    public Model build() {
      return new Model(this);
    }
  }

  public enum Templete {
    ITEM_SONG
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.title);
    dest.writeInt(this.templete == null ? -1 : this.templete.ordinal());
    dest.writeString(this.path);
    dest.writeString(this.artist);
    dest.writeString(this.cover);
    dest.writeParcelable(this.motion, flags);
  }

  protected Model(Parcel in) {
    this.title = in.readString();
    int tmpTemplete = in.readInt();
    this.templete = tmpTemplete == -1 ? null : Templete.values()[tmpTemplete];
    this.path = in.readString();
    this.artist = in.readString();
    this.cover = in.readString();
    this.motion = in.readParcelable(Motion.class.getClassLoader());
  }

  public static final Creator<Model> CREATOR = new Creator<Model>() {
    @Override
    public Model createFromParcel(Parcel source) {
      return new Model(source);
    }

    @Override
    public Model[] newArray(int size) {
      return new Model[size];
    }
  };
}
