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
  private HashMap<Integer, PointerCoords> pointerLocs;

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
    this.pointerLocs = new HashMap<Integer, PointerCoords>();
    setAdapter(new BoardAdapter(context, this));

    setOnTouchListener(new OnTouchListener() {

        public boolean onTouch(View view, MotionEvent event) {

          int pid = event.getPointerId(event.getActionIndex());
          int circleIndex = getPointerPosition(event, pid);

          // touch is outside the view
          if (circleIndex == -1) return true;

          TCircle circle = (TCircle) getItemAtPosition(circleIndex);

          PointerCoords coord;
          switch (event.getActionMasked()) {

            case ACTION_DOWN:
            case ACTION_POINTER_DOWN:

              // get touch location for this pointer
              coord = new PointerCoords();
              event.getPointerCoords(event.findPointerIndex(pid), coord);

              // save the location of this pointer
              pointerLocs.put(pid, coord);

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
              ArrayList<Integer> ids = getPointerIds(event);
              for (int pointerId : ids) {

                // this should never happen, but just to be safe
                if (!pointerLocs.containsKey(pointerId)) {
                  System.out.println("ERROR: no history for pointer " + pointerId);
                  continue;
                }

                PointerCoords startLoc = pointerLocs.get(pointerId);
                PointerCoords endLoc = new PointerCoords();
                event.getPointerCoords(event.findPointerIndex(pointerId), endLoc);

                // update pointer location
                pointerLocs.put(pointerId, endLoc);

                int startCircleIndex = getCircleIndex(startLoc);
                int endCircleIndex = getCircleIndex(endLoc);

                if (startCircleIndex == endCircleIndex) continue;

                TCircle startCircle = circles[startCircleIndex];
                TCircle endCircle = circles[endCircleIndex];

                gameActivity.onMove(startCircleIndex, endCircleIndex, startCircle, endCircle);
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

  private boolean hasMovedCircles(PointerCoords start, PointerCoords end) {
    int startIndex = getCircleIndex(start);
    int endIndex = getCircleIndex(end);
    return start != end;
  }

  private int getCircleIndex(PointerCoords coord) {
    return pointToPosition((int) coord.x, (int) coord.y);
  }

}
