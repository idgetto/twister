package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.util.DisplayMetrics;

public class MenuActivity extends Activity
{
	private static final int PLAYER_ONE_SCORE = 1;
	private static final int PLAYER_TWO_SCORE = 2;
	
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu_activity);

     // calculate the correct circle size
    DisplayMetrics metrics = getResources().getDisplayMetrics();
    int width = metrics.widthPixels;
    int height = metrics.heightPixels;
    int rows = BoardView.ROWS + MoveView.ROWS + 2;
    int cols = BoardView.COLS;
    TCircle.setSize(Math.min(width / cols, height / rows));

  }

  public void spClick(View view)
  {
	 Intent spIntent = new Intent(this, GameActivity.class);
	 spIntent.putExtra("single", true);
	 startActivityForResult(spIntent, PLAYER_ONE_SCORE);
  }
  
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  data.setClass(this, ScoresActivity.class);
	  startActivity(data);
  }

  public void tpClick(View view)
  {
	  Intent tpIntent = new Intent(this, GameActivity.class);
	  tpIntent.putExtra("single", false);
	  startActivity(tpIntent);
  }

  public void scoresClick(View view)
  {
	  Intent scoresIntent = new Intent(this, ScoresActivity.class);
	  startActivity(scoresIntent);
  }

}
