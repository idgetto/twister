package com.moonshine.twister;

import java.util.HashMap;
import java.util.HashSet;

public class TPlayer {

  private HashMap<Finger, Integer> map;
  private HashSet<Integer> pressed;
	public MoveView moveView;
	private int score;

	public TPlayer(MoveView moveView) {
    map = new HashMap<Finger, Integer>();
    pressed = new HashSet<Integer>();
		this.moveView = moveView;
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

  public MoveView getMoveView() {
    return moveView;
  }

  public boolean using(Finger finger, int index) {
	  if (map.get(finger) == index)
		  return true;
    return pressed.contains(index);
  }

  public void press(Finger finger, int index) {
    map.put(finger, index);
    pressed.add(index);
  }
  
  public void release(int index) {
	  pressed.remove(index);
  }

}
