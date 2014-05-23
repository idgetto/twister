/*	
 * TCircle.java
 * Generic Adapter used in the app that keeps padding constant.
 * @author: Kevin You, Isaac GettoMenuActivity
 * Period: 4
 * Date: 05-22-14
 */

package com.moonshine.twister;

import android.content.Context;

public class TCircle {

	private static int size = 100; // default to 100
	private static final int[] images = {
											R.drawable.green_circle,
											R.drawable.yellow_circle,
											R.drawable.blue_circle,
											R.drawable.red_circle
										};

	private static final int[] glowImages = {
											 R.drawable.green_glow_circle,
											 R.drawable.yellow_glow_circle,
											 R.drawable.blue_glow_circle,
											 R.drawable.red_glow_circle
										};

	private TColor color;
	private int imageId;
	private Finger finger;
	private TPlayer player;
	private Context context;

	/* Creates a Circle given a color and context
	 * @param TColor color of the Circle
	 * @param context Context that the TCircle exists in
	 */
	public TCircle(TColor color, Context context) {
		this(color, Finger.NONE, context);
	}

	/* Creates a Circle given a color and context
	 * @param color TColor of the Circle
	 * @param finger Finger that is on the Circle
	 * @param context Context that the TCircle exists in
	 */
	public TCircle(TColor color, Finger finger, Context context) {
		this.color = color;
		this.imageId = images[color.getId()];
		this.finger = finger;
		this.player = null;
		this.context = context;
	}

	/* Checks if the TCircle is required for its player
	 * @return true if the Circle is needed by a TPlayer's moveView
	 */
	public boolean required() {
		if (player == null) return false;
		return player.getMoveView().requires(this);
	}

	/* Sets the TCircle size for drawing
	 * @param size new size
	 */
	public static void setSize(int size) {
		TCircle.size = size;
	}

	/* Gets the TCircle size for drawing
	 * @return size as an int
	 */
	public static int getSize() {
		return size;
	}

	/* Gets the TColor of the TCircle
	 * @return TColor of TCircle
	 */
	public TColor getColor() {
		return color;
	}

	/* Sets the color of the circle
	 * @param color the new TColor
	 */
	public void setColor(TColor color) {
		this.color = color;
		this.imageId = images[color.getId()];
	}

	/* Gets the TCircle's imageId
	 * @return int of imageId
	 */
	public int getImageId() {
		return imageId;
	}

	/* Gets the Finger that is on the TCircle
	 * @return Finger on the TCircle
	 */
	public Finger getFinger() {
		return finger;
	}

	/* Sets the Finger that is on the TCircle
	 * @param finger new Finger on the TCircle
	 */
	public void setFinger(Finger finger) {
		this.finger = finger;
	}

	/* Gets the TPlayer that owns the TC
	 * @return TPlayer that the TCircle belonds to
	 */
	public TPlayer getPlayer() {
		return player;
	}

	/* Sets which TPlayer owns the TCircle
	 * @param player TPlayer who owns the TCircle
	 */
	public void setPlayer(TPlayer player) {
		this.player = player;
	}

	/* Changes imageId to the glow versions
	 */
	public void glow() {
		this.imageId = glowImages[color.getId()];
	}

	/* Changes imageId to the faded versions
	 */
	public void fade() {
		this.imageId = images[color.getId()];
	}

}
