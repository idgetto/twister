package com.moonshine.twister;

import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.Paint;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class MoveAdapter extends BaseAdapter {

  private final int ROWS = 1;
  private final int COLS = 4;

  private Context context;
  private MoveView moveView;
  private Circle[] circles;

  public MoveAdapter(Context context, MoveView moveView) {

    this.context = context;
    this.moveView = moveView;
    circles = new Circle[ROWS * COLS];

    for (int i = 0; i < circles.length; i++) {
      Color[] colors = Color.values();
      Color color = colors[(int) (Math.random() * (colors.length))];

      Finger[] fingers = Finger.values();
      Finger finger = fingers[i + 1];

      circles[i] = new Circle(color, finger, context);
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
    int side = moveView.getColumnWidth();


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

    String finger = circle.getFinger().toString();
    image = labelBitmap(image, finger);

    imageView.setImageBitmap(image);
    return imageView;
  }

  private Bitmap labelBitmap(Bitmap bitmap, String label) {
    Canvas canvas = new Canvas(bitmap);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(android.graphics.Color.WHITE);
    paint.setTextAlign(Paint.Align.CENTER);
    paint.setTextSize(50);

    label = capitalize(label);

    Rect bounds = new Rect();
    paint.getTextBounds("I", 0, 1, bounds);
    int x = bitmap.getWidth() / 2;
    int y = (bitmap.getHeight() + bounds.height()) / 2;

    // from bottom left of text
    canvas.drawText(label, x, y, paint);

    // canvas.drawLine(0, bitmap.getHeight() / 2, bitmap.getWidth(), bitmap.getHeight() / 2, paint);

    return bitmap;
  }

  private String capitalize(String string) {
    String res = "" + Character.toUpperCase(string.charAt(0));
    res += string.substring(1).toLowerCase();
    return res;
  }

}
