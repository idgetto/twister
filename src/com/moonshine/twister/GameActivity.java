package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.content.Intent;
import android.os.Vibrator;

public abstract class GameActivity extends Activity {

  private static final long VIBRATE_TIME = 650;

  protected TPlayer currentPlayer;

  /** Called when the activity is first created. */
    @Override
    public abstract void onCreate(Bundle savedInstanceState);

    public void onPress(int index, TCircle circle) {
			
    if (isFinishing()) return;

    // needs --> does the newest instruction call for this circle
    if (!currentPlayer.using(index) && currentPlayer.moveView.needs(circle)) {
      Finger finger = currentPlayer.moveView.nextFinger();
      currentPlayer.press(index);

      circle.setPlayer(currentPlayer);
      circle.setFinger(finger);

      endMove();
    }
  }

  public void onRelease(int index, TCircle circle) {
    // requries --> is this circle is still necessary
    if (circle.getPlayer().moveView.requires(circle)) {
      gameOver();
    }
    else {
      currentPlayer.release(index);
      circle.setPlayer(null);
      circle.setFinger(Finger.NONE);
    }
  }

  public void onMove(int startIndex, int endIndex, TCircle startCircle, TCircle endCircle) {
    if (startIndex != endIndex) {
  	  onRelease(startIndex, startCircle);
  	  onPress(endIndex, endCircle);
    }
  }


  private void gameOver() {
    if (isFinishing()) return;
    finish();

    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    if (vibrator != null) {
      vibrator.vibrate(VIBRATE_TIME);
    }

    startScores();
  }
	
	protected void update() {
		currentPlayer.moveView.update();
	}

	protected abstract void endMove();

	protected abstract void startScores();

}
