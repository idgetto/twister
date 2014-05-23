/*	
 * MenuActivity
 * Has buttons that lead to te other Activities
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.util.DisplayMetrics;

public class MenuActivity extends BaseActivity {

	private int screenHeight;
	private int screenWidth;

	/* Called when the activity is first created
	 * @param savedInstanceState Bundle of saved instance state 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
	}

	@Override
	/* When window focus changes, resizes window appropriately
	 * @param hasFocus whether or not the app has focus 
	 */
	public void onWindowFocusChanged (boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		View content = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		screenHeight = content.getHeight();
		screenWidth = content.getWidth();
	}

	/* Starts a OnePlayerGame activity
	 * @param view view the menu is in
	 */
	public void onePlayer(View view) {
		int rows = OnePlayerGame.ROWS;
		int cols = OnePlayerGame.COLS;
		TCircle.setSize(Math.min(screenHeight / rows, screenWidth / cols));
		Intent gameIntent = new Intent(this, OnePlayerGame.class);
		startActivity(gameIntent);
	}

	/* Starts a TwoPlayerGame activity
	 * @param view view the menu is in
	 */
	public void twoPlayer(View view) {
		int rows = TwoPlayerGame.ROWS;
		int cols = TwoPlayerGame.COLS;
		TCircle.setSize(Math.min(screenHeight / rows, screenWidth / cols));
		Intent gameIntent = new Intent(this, TwoPlayerGame.class);
		startActivity(gameIntent);
	}

	/* Starts a ScoreActivity with no extras
	 * @param view view the menu is in
	 */
	public void scores(View view) {
		Intent scoresIntent = new Intent(this, ScoresActivity.class);
		startActivity(scoresIntent);
	}

}
