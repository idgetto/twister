 /**
 * TColor.java
 * An enum for the different colors
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
 
package com.moonshine.twister;

public enum TColor {
	GREEN(0),
	YELLOW(1),
	BLUE(2),
	RED(3);

	private int id;

	/**
	 * Creates a new TColor enum
	 */ 
	TColor(int id) {
		this.id = id;
	}

	/**
	 * Returns the color's id
	 * @return the id
	 */ 
	public int getId() {
		return id;
	}

	/**
	 * Returns a random TColor
	 * @return the random color
	 */ 
	public static TColor random() {
		TColor[] colors = TColor.values();
		TColor color = colors[(int) (Math.random() * colors.length)];
		return color;
	}

}
