package com.moonshine.twister;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class BoardView extends GridView {

  public static final int ROWS = 4;
  public static final int COLS = 4;
  public final TCircle[] circles = new TCircle[ROWS * COLS];

  private Context context;

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
    setAdapter(new BoardAdapter(context, this));
  }

}
