package com.moonshine.twister;

import android.graphics.BitmapFactory;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.res.Resources;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;


public class BoardAdapter extends TAdapter {

	private Context context;
  private BoardView boardView;
	private TCircle[] circles;
  private Resources resources;

	public BoardAdapter(Context context, BoardView boardView) {

		this.context = context;
    this.boardView = boardView;
    resources = context.getResources();
    circles = new TCircle[BoardView.ROWS * BoardView.COLS];

    for (int i = 0; i < circles.length; i += 4) {
      circles[i]     = new TCircle(TColor.GREEN, context);
      circles[i + 1] = new TCircle(TColor.YELLOW, context);
      circles[i + 2] = new TCircle(TColor.BLUE, context);
      circles[i + 3] = new TCircle(TColor.RED, context);
    }

	}

	public int getCount() {
    return circles.length;
  }

  public TCircle getItem(int position) {
    return circles[position];
  }

  public long getItemId(int position) {
    return 0;
  }

  // create a new ImageView for each item referenced by the Adapter
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

	  TCircle circle = circles[position];
    Bitmap image = BitmapFactory.decodeResource(context.getResources(), circle.getImageId());
    imageView.setImageBitmap(image);
    return imageView;
  }

}
