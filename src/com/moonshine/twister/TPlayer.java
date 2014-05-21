package com.moonshine.twister;

import java.util.HashSet;

public class TPlayer {

  private HashSet<Integer> pressed;
	public MoveView moveView;
  private int last;
	private int score;

	public TPlayer(MoveView moveView) {
    pressed = new HashSet<Integer>();
		this.moveView = moveView;
    last = -1;
    score = 0;
	}

  public int getScore() {
	  return score;
  }
  
  public void setScore(int points) {
	  score = points;
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
