package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.util.DisplayMetrics;

public class MenuActivity extends Activity {

  private int screenHeight;
  private int screenWidth;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu_activity);
  }

  @Override
  public void onWindowFocusChanged (boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);

    View content = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
    screenHeight = content.getHeight();
    screenWidth = content.getWidth();
  }

  public void onePlayer(View view) {
    int rows = OnePlayerGame.ROWS;
    int cols = OnePlayerGame.COLS;
    TCircle.setSize(Math.min(screenHeight / rows, screenWidth / cols));
		Intent gameIntent = new Intent(this, OnePlayerGame.class);
    startActivity(gameIntent);
  }

  public void twoPlayer(View view) {
    int rows = TwoPlayerGame.ROWS;
    int cols = TwoPlayerGame.COLS;
    TCircle.setSize(Math.min(screenHeight / rows, screenWidth / cols));
		Intent gameIntent = new Intent(this, TwoPlayerGame.class);
    startActivity(gameIntent);
  }

  public void scores(View view) {
		Intent scoresIntent = new Intent(this, ScoresActivity.class);
		startActivity(scoresIntent);
  }

}
