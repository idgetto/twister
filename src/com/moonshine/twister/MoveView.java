package com.moonshine.twister;

import android.widget.GridView;
import android.content.Context;
import android.util.AttributeSet;

public class MoveView extends GridView {

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
  }

}
