package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.content.Intent;
import android.os.Vibrator;

public class GameActivity extends Activity {

  private static final long VIBRATE_TIME = 650;

  private BoardView boardView;
  private MoveView moveView;

  private TPlayer currentPlayer;
  private TPlayer playerOne;
  // private TPlayer playerTwo;

  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.game_activity);

      boardView = (BoardView) findViewById(R.id.board_view);
      moveView  = (MoveView) findViewById(R.id.move_view);

      playerOne = new TPlayer();
      // playerTwo = new TPlayer();
      currentPlayer = playerOne;

      moveView.update();
    }

    public void onPress(int index, TCircle circle) {

      // needs --> does the newest instruction call for this circle
      if (!currentPlayer.using(index) && moveView.needs(circle) && !isFinishing()) {
        Finger finger = moveView.nextFinger();
        currentPlayer.press(index);

        circle.setPlayer(currentPlayer);
        circle.setFinger(finger);

        currentPlayer.incrementScore();

        moveView.update();
      }
    }

    public void onRelease(int index, TCircle circle) {
      // requries --> is this circle is still necessary
      if (moveView.requires(circle)) {
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
      if (!isFinishing()) {
        finish();

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (vibrator != null) {
          vibrator.vibrate(VIBRATE_TIME);
        }

       	Intent scoreIntent = new Intent(getApplicationContext(), ScoresActivity.class);
       	scoreIntent.putExtra("p1score", playerOne.getScore());
       	startActivity(scoreIntent);
       }
    }

  // private void changePlayer() {
  //   currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
  // }

}
