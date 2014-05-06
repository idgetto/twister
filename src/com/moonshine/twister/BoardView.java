package com.moonshine.twister;

import android.widget.GridView;
import android.content.Context;
import android.util.AttributeSet;

public class BoardView extends GridView {

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
  }

}
