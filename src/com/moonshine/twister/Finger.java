package com.moonshine.twister;

public enum Finger {
  NONE(-1);
  INDEX(0);
  MIDDLE(1);
  RING(2);
  PINKY(3);

  private int id;

  Finger(int id) {
    this.id = id;
  }
}
