package com.moonshine.twister;

import java.util.HashSet;

public class TPlayer {

	private int score;
  private HashSet<Integer> pressed;
  private int last;

	public TPlayer() {
    pressed = new HashSet<Integer>();
    last = -1;
    score = 0;
	}

  public int getScore() {
	  return score;
  }
  
  public void setScore(int change) {
	  score = change;
  }
  
  public void incrementScore() {
	  score++;
  }
	
  public boolean using(int index) {
    return (last == index || pressed.contains(index));
  }

  public void press(int index) {
    last = index;
    pressed.add(index);
  }

  public void release(int index) {
    pressed.remove(index);
  }

}
