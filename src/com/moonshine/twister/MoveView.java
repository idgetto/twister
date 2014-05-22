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

  private final TCircle[] circles = new TCircle[ROWS * COLS];
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

    // do nothing (prevents blue selection)
    setOnTouchListener(new OnTouchListener() {
      public boolean onTouch(View v, MotionEvent event) {return true;}
    });
  }

  public TCircle[] getCircles() {
    return circles;
  }

  public TCircle getCircle(int index) {
    return circles[index];
  }

  public Finger nextFinger() {
    return next.getFinger();
  }

  public TColor nextColor() {
    return next.getColor();
  }

  public TCircle nextCircle() {
    return next;
  }

  public void update() {
    int index = (int) (Math.random() * circles.length);

    TColor color = TColor.random();
    Finger finger = Finger.get(index);

    if (circles[index] == null) {
      circles[index] = new TCircle(color, finger, context);
    }
    else {
      TCircle circle = circles[index];
      circle.setColor(color);
      circle.setFinger(finger);
    }

    next = circles[index];
    next.glow();

    invalidateViews();
  }

  // does the newest instruction call for the selected circle
  public boolean needs(TCircle circle) {
    return circle.getColor() == next.getColor();
  }

  // is this circle still necessary
  // based on color and finger
  public boolean requires(TCircle circle) {
    Finger finger = circle.getFinger();

    // accidental tap on unused circle
    System.out.println("start finger: " + finger);
    if (finger == Finger.NONE) return false;

    TCircle required = circles[finger.getId()];
    System.out.println("required color: " + required.getColor());

    // check for same color twice
    // in which case you are allowed to release the color
    System.out.println("required == next " + (required == next));
    if (required == next)
      return false;

    return circle.getColor() == required.getColor();
  }

  public void endMove() {
    next.fade();
    next = null;
    invalidateViews();
  }

}
