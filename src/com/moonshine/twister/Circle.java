package com.moonshine.twister;

public class Circle {

  private final String TYPE = "drawable";

  private int imageId;
  private Finger finger;
  private Context context;

  public Circle(int imageId, Finger finger, Context context) {

    this.imageId = imageId;
    this.finger = finger
    this.context = context;

  }

  public void glow() {
    Resources resources = context.getResources()

    String name = resources.getResourceName(imageId);
    name += "_glow";

    imageId = resources.getIdentifier(name, TYPE, getPackageName())
  }

}
