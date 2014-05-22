package com.moonshine.twister;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import static android.view.MotionEvent.*;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import java.util.HashMap;
import android.view.MotionEvent.PointerCoords;
import java.util.ArrayList;

public class BoardView extends GridView {

  public static final int ROWS = 5;
  public static final int COLS = 4;

  private final TCircle[] circles = new TCircle[ROWS * COLS];
  private GameActivity gameActivity;
  private Context context;
  private HashMap<Integer, Integer> pointerLocs;

	public BoardView(Context context) {
    super(context);
    init(context);
  }

  public BoardView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public BoardView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context);
  }

  private void init(Context context) {
    this.context = context;
    this.gameActivity = (GameActivity) context;
    this.pointerLocs = new HashMap<Integer, Integer>();
    setAdapter(new BoardAdapter(context, this));

    setOnTouchListener(new OnTouchListener() {

        public boolean onTouch(View view, MotionEvent event) {

          int pid = event.getPointerId(event.getActionIndex());
          int circleIndex = getPointerPosition(event, pid);

          // touch is outside the view
          // if (circleIndex == -1) return true;

          TCircle circle = (TCircle) getItemAtPosition(circleIndex);

          switch (event.getActionMasked()) {

            case ACTION_DOWN:
            case ACTION_POINTER_DOWN:

              // save the location of this pointer
              pointerLocs.put(pid, getPointerPosition(event, pid));

              gameActivity.onPress(circleIndex, circle);
              break;

            case ACTION_UP:
            case ACTION_POINTER_UP:

              // remove pointer location
              pointerLocs.remove(pid);

              gameActivity.onRelease(circleIndex, circle);
              break;

            case ACTION_MOVE:
              // ACTION_MOVE can detect movements of all pointers,
              // but it doesn't know which pointer caused the event.
              // So we must do this for all pointers
              for (int pointerId : getPointerIds(event)) {
                int startCircleIndex = pointerLocs.get(pointerId);
                int endCircleIndex = getPointerPosition(event, pointerId);

                if (startCircleIndex == endCircleIndex) continue;

                // update pointer location
                if (endCircleIndex != -1)
                  pointerLocs.put(pointerId, endCircleIndex);

                TCircle startCircle = startCircleIndex != -1 ? circles[startCircleIndex] : null;
                TCircle endCircle = endCircleIndex != -1 ? circles[endCircleIndex] : null;

                gameActivity.onRelease(startCircleIndex, startCircle);
                gameActivity.onPress(endCircleIndex, endCircle);
              }

              break;

          }

          return true;

        }

      });
  }

  public TCircle[] getCircles() {
    return circles;
  }

  public TCircle getCircle(int index) {
    return circles[index];
  }

  private ArrayList<Integer> getPointerIds(MotionEvent event) {
    ArrayList<Integer> ids = new ArrayList<Integer>();
    int count = event.getPointerCount();
    for (int i = 0; i < count; i++) {
      int id = event.getPointerId(i);
      ids.add(id);
    }
    return ids;
  }

  private int getPointerPosition(MotionEvent event, int pointerId) {
    int x = (int) event.getX(event.findPointerIndex(pointerId));
    int y = (int) event.getY(event.findPointerIndex(pointerId));
    return pointToPosition(x, y);
  }

}
