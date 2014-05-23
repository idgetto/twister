/*	
 * MoveAdapter.java
 * Allows interaction with MoveView's array of circles,
 * and converts it to ImageViews
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

import android.graphics.BitmapFactory;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;

public class MoveAdapter extends TAdapter {

	private Context context;
	private MoveView moveView;
	
	/* Creates a MoveAdapter that has a moveView
	 * @param context Context of the moveView
	 * @param moveView MoveView that is being interacted withh
	 */
	public MoveAdapter(Context context, MoveView moveView) {
		this.context = context;
		this.moveView = moveView;
	}

	/* Gets the number of circles in the moveView
	 * @return int number of circles in moveview's array
	 */
	public int getCount() {
		return moveView.getCircles().length;
	}

	/* Gets the TCircle at an index (0-based)
	 * @param position index as an int
	 * @return TCircle that is at the index in moveview's array
	 */
	public TCircle getItem(int position) {
		return moveView.getCircle(position);
	}

	/* Retrieves long value of the item - unused.
	 * @return 0
	 */
	public long getItemId(int position) {
		return 0;
	}

	/* Create a new ImageView for each item references by the Adapter
	 * @param position position of TCircle
	 * @param convertView view that might be recycled
	 * @param parent unused
	 * @return View imageView with circle image
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(context);

		imageView.setLayoutParams(new GridView.LayoutParams(TCircle.getSize(), TCircle.getSize()));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setPadding(PADDING, PADDING, PADDING, PADDING);

		TCircle circle = moveView.getCircle(position);

		// should be blank in no move is set for this circle
		if (circle == null) return imageView;

		Bitmap image = BitmapFactory.decodeResource(context.getResources(), circle.getImageId());

		String finger = circle.getFinger().toString();
		finger = Utils.capitalize(finger);

		// not sure if this text size will look good across devices
		image = Utils.labelBitmap(image, finger, image.getWidth() / 4);

		imageView.setImageBitmap(image);
		return imageView;
	}

}
