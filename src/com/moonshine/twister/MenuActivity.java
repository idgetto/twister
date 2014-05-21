package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.util.DisplayMetrics;

public class MenuActivity extends Activity {

	private static final int GAME_ACTIVITY = 1;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu_activity);

    // calculate the correct circle size
    DisplayMetrics metrics = getResources().getDisplayMetrics();
    int width = metrics.widthPixels;
    int height = metrics.heightPixels;
    int rows = BoardView.ROWS + MoveView.ROWS;
    int cols = BoardView.COLS;
    TCircle.setSize(Math.min(width / cols, height / rows));

  }

  public void onePlayer(View view) {
	Intent gameIntent = new Intent(this, OnePlayerGame.class);
    	startActivity(gameIntent);
  }

  public void twoPlayer(View view) {
	Intent gameIntent = new Intent(this, TwoPlayerGame.class);
    	startActivity(gameIntent);
  }

  public void scores(View view) {
	Intent scoresIntent = new Intent(this, ScoresActivity.class);
	startActivity(scoresIntent);
  }

}
