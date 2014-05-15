package com.moonshine.twister;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.GridView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MoveView extends GridView {

  public static final int ROWS = 1;
  public static final int COLS = 4;
  public final TCircle[] circles = new TCircle[ROWS * COLS];

  private Context context;
  private TCircle next;

  public MoveView(Context context) {
    super(context);
    init(context);
  }

  public MoveView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public MoveView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context);
  }

  private void init(Context context) {
    this.context = context;
    setAdapter(new MoveAdapter(context, this));
    setBackgroundColor(Color.LTGRAY);
    setOnTouchListener(new OnTouchListener() {
      public boolean onTouch(View v, MotionEvent event) {return true;}
    });
  }

  public Finger nextFinger() {
    return next.getFinger();
  }

  public void update() {
    // next.fade();

    int index = (int) (Math.random() * circles.length);

    TColor[] colors = TColor.values();
    TColor color = colors[(int) (Math.random() * colors.length)];

    Finger[] fingers = Finger.values();
    Finger finger = fingers[index + 1];

    if (circles[index] == null) {
      circles[index] = new TCircle(color, finger, context);
    }
    else {
      TCircle circle = circles[index];
      circle.setColor(color);
      circle.setFinger(finger);
    }
    next = circles[index];

    // refreshes the gridView
    invalidateViews();
  }

  // does the newest instruction call for the selected circle
  public boolean needs(TCircle circle) {
    return circle.getColor() == next.getColor();
  }

  // is this circle still necessary
  public boolean requires(TCircle circle) {
    Finger finger = circle.getFinger();
    TCircle required = circles[finger.getId()];
    return circle.getColor() == required.getColor();
  }

}
