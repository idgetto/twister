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

  private int colorIndex;
  private int imageId;
  private Finger finger;
  private Context context;

  public Circle(int colorIndex, Finger finger, Context context) {

    this.colorIndex = colorIndex;
    this.imageId = images[colorIndex];
    this.finger = finger;
    this.context = context;

  }

  public int getImageId() {
    return imageId;
  }

  public void glow() {
    this.imageId = glowImages[colorIndex];
  }

  // reverse of glow
  public void fade() {
    this.imageId = images[colorIndex];
  }

}
