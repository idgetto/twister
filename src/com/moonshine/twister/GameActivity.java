/*	
 * GameActivity.java
 * Abstract GameActivity that handles press and release
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.content.Intent;
import android.os.Vibrator;

public abstract class GameActivity extends BaseActivity {

	private static final long VIBRATE_TIME = 650;

	protected TPlayer currentPlayer;

	/* Called when the activity is first created
	 * @param savedInstanceState Bundle of saved instance state 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/* Called when "pressed" game logic must occur
	 * @param index index of the circle
	 * @param circle TCircle that was pressed
	 */
	public void onPress(int index, TCircle circle) {
		if (circle == null || isFinishing()) return;

		// needs --> does the newest instruction call for this circle
		MoveView moveView = currentPlayer.getMoveView();
		Finger finger = moveView.nextFinger();
		if (!currentPlayer.using(finger, index) && moveView.needs(circle)) {
			currentPlayer.press(finger, index);

			circle.setPlayer(currentPlayer);
			circle.setFinger(finger);

			moveView.endMove();
			endMove();
		}
	}

	/* Called when "released"game logic must occur
	 * @param index index of the circle
	 * @param circle TCircle that was released
	 */
	public void onRelease(int index, TCircle circle) {
		if (circle == null || isFinishing()) return;

		// requries --> is this circle is still necessary
		if (circle.required()) {
			gameOver(circle.getPlayer());
		}
		else {
			currentPlayer.release(index);
			circle.setPlayer(null);
			circle.setFinger(Finger.NONE);
		}
	}

	/* Signals the end of the game.
	 * @param loser TPlayer that caused the loss
	 */
	private void gameOver(TPlayer loser) {
		if (isFinishing()) return;
		finish();

		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		if (vibrator != null) {
			vibrator.vibrate(VIBRATE_TIME);
		}

		startScores(loser);
	}

	/* Calls to update the current player's moveView
	 */
	protected void update() {
		currentPlayer.getMoveView().update();
	}

	/* Ends the current player's move
	 */
	protected abstract void endMove();

	/* Starts scoreActivity
	 * @param loser TPlayer that caused the loss
	 */
	protected abstract void startScores(TPlayer loser);

}
