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
    Finger finger = moveView.nextFinger();
    if (!currentPlayer.using(finger, index) && moveView.needs(circle)) {
      currentPlayer.press(finger, index);

      circle.setPlayer(currentPlayer);
      circle.setFinger(finger);

      moveView.endMove();
      endMove();
    }
  }

  public void onRelease(int index, TCircle circle) {
    if (circle == null || isFinishing()) return; 
    
    // requries --> is this circle is still necessary
    System.out.println("Released index: " + index);
    if (circle.required()) {
      gameOver(circle.getPlayer());
    }
    else {
      currentPlayer.release(index);
      circle.setPlayer(null);
      circle.setFinger(Finger.NONE);
    }
  }

  private void gameOver(TPlayer loser) {
    if (isFinishing()) return;
    finish();

    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    if (vibrator != null) {
      vibrator.vibrate(VIBRATE_TIME);
    }

    startScores(loser);
  }

	protected void update() {
		currentPlayer.getMoveView().update();
	}

	protected abstract void endMove();

	protected abstract void startScores(TPlayer loser);

}
