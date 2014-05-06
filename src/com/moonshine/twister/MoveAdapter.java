package com.moonshine.twister;

import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.view.View;

public class MoveAdapter extends BaseAdapter {

  private final int ROWS = 1;
  private final int COLS = 4;

  private Context context;
  private Circle[] circles;

  public MoveAdapter(Context context) {

    this.context = context;
    circles = new Circle[ROWS * COLS];

    for (int i = 0; i < circles.length; i++) {
      circles[i] = new Circle(Color.RED, Finger.INDEX, context);
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
    if (convertView == null) {  // if it's not recycled, initialize some attributes
      imageView = new ImageView(context);
      imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(8, 8, 8, 8);
    } else {
      imageView = (ImageView) convertView;
    }

    Circle circle = circles[position];
    imageView.setImageResource(circle.getImageId());
    return imageView;
  }

}
