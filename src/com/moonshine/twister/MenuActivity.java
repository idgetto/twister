package com.moonshine.twister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu_activity);

  }

  public void spClick(View view)
  {
	 Intent spIntent = new Intent(this, GameActivity.class);
	 spIntent.putExtra("single", true);
	 startActivity(spIntent);
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
	  scoresIntent.putExtra("menu", true);
	  startActivity(scoresIntent);
  }

}
