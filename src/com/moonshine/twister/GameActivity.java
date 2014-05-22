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
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public void onPress(int index, TCircle circle) {
    if (circle == null || isFinishing()) return;

    // needs --> does the newest instruction call for this circle
    MoveView moveView = currentPlayer.getMoveView();
    if (!currentPlayer.using(index) && moveView.needs(circle)) {
      Finger finger = moveView.nextFinger();
      currentPlayer.press(index);

      circle.setPlayer(currentPlayer);
      circle.setFinger(finger);

      moveView.endMove();
      endMove();
    }
  }

  public void onRelease(int index, TCircle circle) {
    // requries --> is this circle is still necessary
    System.out.println("Released index: " + index);
    if (circle.required()) {
      gameOver();
    }
    else {
      currentPlayer.release(index);
      circle.setPlayer(null);
      circle.setFinger(Finger.NONE);
    }
  }

  public void onMove(int startIndex, int endIndex, TCircle startCircle, TCircle endCircle) {
    System.out.println("Moved from " + startIndex + " to " + endIndex);
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
		currentPlayer.getMoveView().update();
	}

	protected abstract void endMove();

	protected abstract void startScores();

}
