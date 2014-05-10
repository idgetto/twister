package com.moonshine.twister;

import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BoardAdapter extends BaseAdapter {

	private final int ROWS = 4;
	private final int COLS = 4;

	private Context context;
  private GridView gridView;
	private Circle[] circles;
  private Resources resources;

	public BoardAdapter(Context context, GridView gridView) {

		this.context = context;
    this.gridView = gridView;
    System.out.println("About to ger resources");
    resources = context.getResources();
    circles = new Circle[ROWS * COLS];

    for (int i = 0; i < circles.length; i += 4) {
      circles[i]     = new Circle(Color.GREEN, context);
      circles[i + 1] = new Circle(Color.YELLOW, context);
      circles[i + 2] = new Circle(Color.BLUE, context);
      circles[i + 3] = new Circle(Color.RED, context);
    }

	}

	public int getCount() {
    return circles.length;
  }

  public Circle getItem(int position) {
    return circles[position];
  }

  public long getItemId(int position) {
    return 0;
  }

  // create a new ImageView for each item referenced by the Adapter
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;

    /* we need to get the column width in order
     * for the circles to be dynamically sized
     * on different sized devices.
     */
    int side = gridView.getColumnWidth();


    if (convertView == null) {  // if it's not recycled, initialize some attributes
      imageView = new ImageView(context);
      imageView.setLayoutParams(new GridView.LayoutParams(side, side));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(8, 8, 8, 8);
    } else {
      imageView = (ImageView) convertView;
    }

	  Circle circle = circles[position];
    Bitmap image = BitmapFactory.decodeResource(context.getResources(), circle.getImageId());
    image = Bitmap.createScaledBitmap(image, side, side, false);
    imageView.setImageBitmap(image);
    return imageView;
  }

}
