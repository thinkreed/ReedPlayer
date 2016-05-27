package com.reed.reedplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thinkreed on 16/5/4.
 */
public class Model implements Parcelable {
  public Templete templete;
  public Motion motion;
  public Song song;

  private Model(Builder builder) {
    templete = builder.templete;
    motion = builder.motion;
    song = builder.song;
  }

  @Override
  public String toString() {
    return "Model{" +
        "templete=" + templete +
        ", motion=" + motion +
        ", song=" + song +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Model model = (Model) o;

    if (templete != model.templete) return false;
    if (motion != null ? !motion.equals(model.motion) : model.motion != null) return false;
    return song != null ? song.equals(model.song) : model.song == null;

  }

  @Override
  public int hashCode() {
    int result = templete != null ? templete.hashCode() : 0;
    result = 31 * result + (motion != null ? motion.hashCode() : 0);
    result = 31 * result + (song != null ? song.hashCode() : 0);
    return result;
  }

  public enum Templete {
    ITEM_SONG
  }

  public static final class Builder {
    private Templete templete;
    private Motion motion;
    private Song song;

    public Builder() {}

    public Builder(Model copy) {
      this.templete = copy.templete;
      this.motion = copy.motion;
      this.song = copy.song;
    }

    public Builder templete(Templete val) {
      templete = val;
      return this;
    }

    public Builder motion(Motion val) {
      motion = val;
      return this;
    }

    public Builder song(Song val) {
      song = val;
      return this;
    }

    public Model build() {
      return new Model(this);
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.templete == null ? -1 : this.templete.ordinal());
    dest.writeParcelable(this.motion, flags);
    dest.writeParcelable(this.song, flags);
  }

  protected Model(Parcel in) {
    int tmpTemplete = in.readInt();
    this.templete = tmpTemplete == -1 ? null : Templete.values()[tmpTemplete];
    this.motion = in.readParcelable(Motion.class.getClassLoader());
    this.song = in.readParcelable(Song.class.getClassLoader());
  }

  public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
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
