package com.moonshine.twister;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Utils {

  public static String capitalize(String string) {
    String res = "" + Character.toUpperCase(string.charAt(0));
    res += string.substring(1).toLowerCase();
    return res;
  }

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
