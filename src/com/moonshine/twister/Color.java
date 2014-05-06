package com.moonshine.twister;

public enum Color {
  GREEN(0),
  YELLOW(1),
  BLUE(2),
  RED(3);

  private int id;

  Color(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

}
