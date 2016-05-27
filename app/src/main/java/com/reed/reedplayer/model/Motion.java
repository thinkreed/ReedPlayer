package com.reed.reedplayer.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thinkreed on 16/5/24.
 */
public class Motion implements Parcelable {

  public Type type;
  public Bundle bundle;
  public Class clazz;

  @Override
  public String toString() {
    return "Motion{" +
        "type=" + type +
        ", bundle=" + bundle +
        ", clazz=" + clazz +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Motion motion = (Motion) o;

    if (type != motion.type) return false;
    if (bundle != null ? !bundle.equals(motion.bundle) : motion.bundle != null) return false;
    return clazz != null ? clazz.equals(motion.clazz) : motion.clazz == null;

  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (bundle != null ? bundle.hashCode() : 0);
    result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
    return result;
  }

  private Motion(Builder builder) {
    type = builder.type;
    bundle = builder.bundle;
    clazz = builder.clazz;
  }


  enum Type {
    JUMP
  }

  public Motion() {}

  public static final class Builder {
    private Type type;
    private Bundle bundle;
    private Class clazz;

    public Builder() {}

    public Builder(Motion copy) {
      this.type = copy.type;
      this.bundle = copy.bundle;
      this.clazz = copy.clazz;
    }

    public Builder type(Type val) {
      type = val;
      return this;
    }

    public Builder bundle(Bundle val) {
      bundle = val;
      return this;
    }

    public Builder clazz(Class val) {
      clazz = val;
      return this;
    }

    public Motion build() {
      return new Motion(this);
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.type == null ? -1 : this.type.ordinal());
    dest.writeBundle(this.bundle);
    dest.writeSerializable(this.clazz);
  }

  protected Motion(Parcel in) {
    int tmpType = in.readInt();
    this.type = tmpType == -1 ? null : Type.values()[tmpType];
    this.bundle = in.readBundle();
    this.clazz = (Class) in.readSerializable();
  }

  public static final Creator<Motion> CREATOR = new Creator<Motion>() {
    @Override
    public Motion createFromParcel(Parcel source) {
      return new Motion(source);
    }

    @Override
    public Motion[] newArray(int size) {
      return new Motion[size];
    }
  };
}
