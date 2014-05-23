/*	
 * Utils.java
 * Provides utility methods for the 
 * rest of the application
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
 
package com.moonshine.twister;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Utils {
	
	
	/**
	 * Returns a String with the first
	 * letter capitalized and the reset lower case
	 * @param string the string to capitalize
	 * @return the capitalized string
	 */
	public static String capitalize(String string) {
		String res = "" + Character.toUpperCase(string.charAt(0));
		res += string.substring(1).toLowerCase();
		return res;
	}
	
	/**
	 * Labels a bitmap with a given text
	 * @param bitmap the bitmap to label
	 * @param label the text to draw
	 * @param textSize the text size to use
	 * @return the labeled bitmap
	 */ 
	public static Bitmap labelBitmap(Bitmap bitmap, String label, int textSize) {

		Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvas = new Canvas(copy);

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(textSize);

		Rect bounds = new Rect();
		paint.getTextBounds("I", 0, 1, bounds);
		int x = copy.getWidth() / 2;
		int y = (copy.getHeight() + bounds.height()) / 2;

		// from bottom left of text
		canvas.drawText(label, x, y, paint);

		// canvas.drawLine(0, copy.getHeight() / 2, copy.getWidth(), copy.getHeight() / 2, paint);

		return copy;
	}

}
