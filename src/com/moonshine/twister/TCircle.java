package com.moonshine.twister;

import android.content.Context;

public class TCircle {

  private static int size = 100; // default to 100
  private static final int[] images = {
                                        R.drawable.green_circle,
                                        R.drawable.yellow_circle,
                                        R.drawable.blue_circle,
                                        R.drawable.red_circle
                                      };

  private static final int[] glowImages = {
                                             R.drawable.green_glow_circle,
                                             R.drawable.yellow_glow_circle,
                                             R.drawable.blue_glow_circle,
                                             R.drawable.red_glow_circle
                                          };

  private TColor color;
  private int imageId;
  private Finger finger;
  private TPlayer player;
  private Context context;

  public TCircle(TColor color, Context context) {
    this(color, Finger.NONE, context);
  }

  public TCircle(TColor color, Finger finger, Context context) {
    this.color = color;
    this.imageId = images[color.getId()];
    this.finger = finger;
    this.player = null;
    this.context = context;
  }

  public static void setSize(int size) {
    TCircle.size = size;
  }

  public static int getSize() {
    return size;
  }

  public TColor getColor() {
    return color;
  }

  public void setColor(TColor color) {
    this.color = color;
    this.imageId = images[color.getId()];
  }

  public int getImageId() {
    return imageId;
  }

  public Finger getFinger() {
    return finger;
  }

  public void setFinger(Finger finger) {
    this.finger = finger;
  }

  public TPlayer getPlayer() {
    return player;
  }

  public void setPlayer(TPlayer player) {
    this.player = player;
  }

  public void glow() {
    this.imageId = glowImages[color.getId()];
  }

  // reverse of glow
  public void fade() {
    this.imageId = images[color.getId()];
  }

}
