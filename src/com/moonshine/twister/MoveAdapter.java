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

  public MoveAdapter(Context context, MoveView moveView) {
    this.context = context;
    this.moveView = moveView;
  }

  public int getCount() {
    return moveView.getCircles().length;
  }

  public TCircle getItem(int position) {
    return moveView.getCircle(position);
  }

  public long getItemId(int position) {
    return 0;
  }

  // create a new ImageView for each item referenced by the Adapter
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
