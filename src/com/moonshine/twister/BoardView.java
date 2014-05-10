package com.moonshine.twister;

import android.widget.GridView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import static android.view.MotionEvent.*;
import android.view.View.OnTouchListener;
import android.view.View;
import android.widget.Toast;

public class BoardView extends GridView {

  private Context context;

	public BoardView(Context context) {
    super(context);
    System.out.println("Inside boardView constructor");
    init(context);
  }

  public BoardView(Context context, AttributeSet attrs) {
    super(context, attrs);
    System.out.println("Inside boardView constructor");
    init(context);
  }

  public BoardView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    System.out.println("Inside boardView constructor");
    init(context);
  }

  private void init(Context context) {
    this.context = context;
    setAdapter(new BoardAdapter(context, this));

    final Context aContext = context;
    setOnTouchListener(new OnTouchListener() {

      public boolean onTouch(View v, MotionEvent event) {

        int eventX = (int) event.getX();
        int eventY = (int) event.getY();
        int index = pointToPosition(eventX, eventY);
        Circle circle = (Circle) getItemAtPosition(index);
        System.out.println("Event!!!" + index);

        switch (event.getAction()) {

          case ACTION_DOWN:
              Toast.makeText(aContext, "Touched circle at index: " + index, Toast.LENGTH_SHORT).show();
            // textView.setText("Touched circle at index: " + index);
            break;

          case ACTION_UP:
              Toast.makeText(aContext, "Let go of circle at index: " + index, Toast.LENGTH_SHORT).show();
            // textView.setText("Let go of circle at index: " + index);
            break;

        }

        return true;

      }

    });
  }

}
