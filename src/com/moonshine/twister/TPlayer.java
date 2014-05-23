/**
 * TPlayer.java
 * Represets a human player and 
 * manages their score and touches
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

import java.util.HashMap;
import java.util.HashSet;

public class TPlayer {

	private HashMap<Finger, Integer> lastPressed;
	private HashSet<Integer> pressed;
	public MoveView moveView;
	private int score;
	
	/**
	 * Creates a new TPplayer
	 * @param moveView the MoveView associated with this TPlayer
	 */ 
	public TPlayer(MoveView moveView) {
		lastPressed = new HashMap<Finger, Integer>();
		pressed = new HashSet<Integer>();
		this.moveView = moveView;
		score = 0;
	}

	/**
	 * Returns the player's score
	 * @return the player's score
	 */ 
	public int getScore() {
		return score;
	}

	/**
	 * Sets the player's score
	 * @param points the score to set
	 */ 
	public void setScore(int points) {
		score = points;
	}

	/**
	 * Increments the player's score
	 */ 
	public void incrementScore() {
		score++;
	}
	
	/**
	 * Returns the player's MoveView
	 * @return the MoveView 
	 */ 
	public MoveView getMoveView() {
		return moveView;
	}

	/**
	 * Checks if the player is already
	 * using a given circle
	 * @param finger the finger attempting to press a circle
	 * @param index the circle index being touched
	 * @return whether the circle is in use
	 */ 
	public boolean using(Finger finger, int index) {
		if (lastPressed.containsKey(finger) && lastPressed.get(finger) == index)
			return true;
		return pressed.contains(index);
	}

	/**
	 * Adds the pressed circle to the player's
	 * list of used circles
	 * @param finger the finger pressing the circle
	 * @param index the circle index being touched
	 */ 
	public void press(Finger finger, int index) {
		lastPressed.put(finger, index);
		pressed.add(index);
	}

	/**
	 * Removes the circle from the player's
	 * list of used circle
	 */ 
	public void release(int index) {
		pressed.remove(index);
	}

}
