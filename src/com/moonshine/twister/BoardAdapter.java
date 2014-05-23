/*	
 * BoardAdapter.java
 * Converts the grid of Circles from BoardView into a array of images
 * and helps GameActivity interact with the grid of circles
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

import android.graphics.BitmapFactory;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.content.res.Resources;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;

public class BoardAdapter extends TAdapter {

	private Context context;
	private BoardView boardView;
	private Resources resources;

	/* Creates a BoardAdapter, filling the boardView's grid of circles
	 * and setting instance variables.
	 * @param context Context of the game
	 * @param boardView BoardView that is being adapted
	 */
	public BoardAdapter(Context context, BoardView boardView) {
		this.context = context;
		this.boardView = boardView;
		resources = context.getResources();
	
		TCircle[] circles = boardView.getCircles();
		for (int i = 0; i < circles.length; i += TColor.values().length) {
			circles[i]	 = new TCircle(TColor.GREEN, context);
			circles[i + 1] = new TCircle(TColor.YELLOW, context);
			circles[i + 2] = new TCircle(TColor.BLUE, context);
			circles[i + 3] = new TCircle(TColor.RED, context);
		}
	}

	/* Counts the number of circles
 	* @return number of circles in boardView's grid of circles
 	*/
	public int getCount() {
		return boardView.getCircles().length;
	}
  
	/* Retrieves the Circle at the (0 based) index
	 * @param position index as a 0-based index
	 * @return Circle at index position
	 */
	public TCircle getItem(int position) {
		return boardView.getCircle(position);
	}
	
	/* Retrieves long value of the grid - unused.
	 * @return 0
	 */
	public long getItemId(int position) {
		return 0;
	}
	
	/* Create a new ImageView for each item referenced by the Adapter
	 * @param position position of circle
	 * @param convertView view that might be recycled
	 * @param parent unused
	 * @return View imageView with circle image
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(TCircle.getSize(), TCircle.getSize()));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
		} else {
			imageView = (ImageView) convertView;
		}
	
		TCircle circle = boardView.getCircle(position);
		Bitmap image = BitmapFactory.decodeResource(context.getResources(), circle.getImageId());
		imageView.setImageBitmap(image);
		return imageView;
	}
	
}
