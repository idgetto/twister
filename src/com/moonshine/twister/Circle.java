package com.moonshine.twister;

import android.content.Context;

public class Circle {

  // TODO: add images
  private static final int[] images = {
                                        // R.drawable.green_circle,
                                        // R.drawable.yellow_circle,
                                        // R.drawable.blue_circle,
                                        // R.drawable.red_circle
                                      };

  private static final int[] glowImages = {
                                            // R.drawable.green_glow_circle,
                                            // R.drawable.yellow_glow_circle,
                                            // R.drawable.blue_glow_circle,
                                            // R.drawable.red_glow_circle
                                          };

  private Color color;
  private int imageId;
  private Finger finger;
  private Player player;
  private Context context;

  public Circle(Color color, Context context) {
    this(color, Finger.NONE, context);
  }

  public Circle(Color color, Finger finger, Context context) {
    this.color = color;
    this.imageId = images[color.getId()];
    this.finger = finger;
    this.player = null;
    this.context = context;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getImageId() {
    return imageId;
  }

  public void glow() {
    this.imageId = glowImages[color.getId()];
  }

  // reverse of glow
  public void fade() {
    this.imageId = images[color.getId()];
  }

}
