/*	
 * Finger.java
 * Enum of the four different Fingers.
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

public enum Finger {
	NONE(-1),
	INDEX(0),
	MIDDLE(1),
	RING(2),
	PINKY(3);

	private int id;

	/* Creates a new Finger
	 * @param id id of Finger
	 */
	Finger(int id) {
		this.id = id;
	}
	
	/* Get the id of Finger
	 * @return id of Finger
	 */
	public int getId() {
		return id;
	}
	
	/* Converts id to a value of Finger
	 * @param id id that corresponds to a Finger
	 * @return Finger as a value
	 */
	public static Finger get(int id) {
		return Finger.values()[id + 1];
	}
}