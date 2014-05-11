package com.moonshine.twister;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.GridView;

public class MoveView extends GridView {

  public static final int ROWS = 1;
  public static final int COLS = 4;

  private Context context;

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
  }

}
