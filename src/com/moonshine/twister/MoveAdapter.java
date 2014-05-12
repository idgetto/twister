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
  private TCircle[] circles;

  public MoveAdapter(Context context, MoveView moveView) {

    this.context = context;
    this.moveView = moveView;
    circles = new TCircle[moveView.ROWS * moveView.COLS];

    for (int i = 0; i < circles.length; i++) {
      TColor[] colors = TColor.values();
      TColor color = colors[(int) (Math.random() * (colors.length))];

      Finger[] fingers = Finger.values();
      Finger finger = fingers[i + 1];

      circles[i] = new TCircle(color, finger, context);
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

    String finger = circle.getFinger().toString();
    finger = Utils.capitalize(finger);

    // not sure if this text size will look good across devices
    image = Utils.labelBitmap(image, finger, image.getWidth() / 4);

    imageView.setImageBitmap(image);
    return imageView;
  }

}
