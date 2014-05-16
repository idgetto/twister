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

  public static final int ROWS = 4;
  public static final int COLS = 4;
  public final TCircle[] circles = new TCircle[ROWS * COLS];

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

          int eventX = (int) event.getX(event.findPointerIndex(pid));
          int eventY = (int) event.getY(event.findPointerIndex(pid));
          int index = pointToPosition(eventX, eventY);

          if (index == -1) return true;

          TCircle circle = (TCircle) getItemAtPosition(index);

          System.out.println("event: " + event.getActionMasked());
          PointerCoords coord;
          switch (event.getActionMasked()) {

            case ACTION_DOWN:
            case ACTION_POINTER_DOWN:

              // get touch location for this pointer
              coord = new PointerCoords();
              event.getPointerCoords(event.findPointerIndex(pid), coord);

              pointerLocs.put(pid, coord);

              System.out.println("Inside action down event");
              gameActivity.onPress(index, circle);
              break;

            case ACTION_UP:
            case ACTION_POINTER_UP:

              // remove pointer loc
              pointerLocs.remove(pid);

              System.out.println("Inside action up event");
              gameActivity.onRelease(index, circle);
              break;

            case ACTION_MOVE:

              // ACTION_MOVE can detect movements of all pointers,
              // but it doesn't know which pointer caused the event.
              // So we must do this for all pointers
              ArrayList<Integer> ids = getPointerIds(event);
              for (int pointerId : ids) {

                if (!pointerLocs.containsKey(pointerId)) continue;

                // retrieve start location
                PointerCoords startLoc = pointerLocs.get(pointerId);
                int startIndex = pointToPosition((int) startLoc.x, (int) startLoc.y);

                if (event.findPointerIndex(pointerId) == -1) continue;

                int x = (int) event.getX(event.findPointerIndex(pointerId));
                int y = (int) event.getY(event.findPointerIndex(pointerId));
                int endIndex = pointToPosition(x, y);
                if (endIndex == -1) continue;

                // only care about moving to another circle
                if (startIndex == endIndex) continue;

                // remove pointer loc
                pointerLocs.remove(pid);
                
                coord = new PointerCoords();
                event.getPointerCoords(event.findPointerIndex(pid), coord);

                pointerLocs.put(pid, coord);
                
                TCircle startCircle = circles[startIndex];
                
                TCircle endCircle = circles[endIndex];
                System.out.println("Pointer id #" + pointerId + " moved from " + startIndex + " to " + endIndex);

                System.out.println("Inside action move event");
                gameActivity.onMove(startIndex, endIndex, startCircle, endCircle);
                
                

              }

              break;

          }

          return true;

        }

      });
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

}
