package com.reed.reedplayer.model;

/**
 * Created by thinkreed on 16/5/4.
 */
public class Model {
  public String title;
  public Templete templete;
  public String path;
  public String artist;
  public String cover;

  private Model(Builder builder) {
    this.title = builder.title;
    this.templete = builder.templete;
    this.path = builder.path;
    this.artist = builder.artist;
    this.cover = builder.cover;
  }

  public static final class Builder {
    String title;
    Templete templete;
    String path;
    String artist;
    String cover;

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

    public Model build() {
      return new Model(this);
    }
  }

  public enum Templete {
    ITEM_SONG
  }
}
