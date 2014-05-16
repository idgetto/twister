package com.moonshine.twister;

import static android.view.MotionEvent.*;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.GridView;
import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnTouchListener;
import android.view.View;

public class GameActivity extends Activity {

  private BoardView boardView;
  private MoveView moveView;
  private TextView textView;

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
      textView  = (TextView) findViewById(R.id.text_view);

      playerOne = new TPlayer();
      // playerTwo = new TPlayer();
      currentPlayer = playerOne;

      moveView.update();
    }

    public void onPress(int index, TCircle circle) {
      textView.append("Touched index: " + index);

      // needs --> does the newest instruction call for this circle
      if (!currentPlayer.using(index) && moveView.needs(circle)) {
        textView.setText("Success! Index: " + index);

        Finger finger = moveView.nextFinger();
        currentPlayer.press(index);

        circle.setPlayer(currentPlayer);
        circle.setFinger(finger);

        moveView.update();
        textView.append("\nColor: "  + Utils.capitalize(moveView.nextColor().toString()));
        textView.append("\nFinger: " + Utils.capitalize(moveView.nextFinger().toString()));
      }
    }

    public void onRelease(int index, TCircle circle) {
      // requries --> is this circle is still necessary
      if (moveView.requires(circle))
        textView.append("Game Over!");
        // gameOver();
      else {
        currentPlayer.release(index);
        circle.setPlayer(null);
        circle.setFinger(Finger.NONE);
      }
    }

    public void onMove(int startIndex, int endIndex, TCircle origCircle, TCircle circle) {
      textView.setText("Moved to index " + endIndex);
      textView.append("\nMoved from index: " + startIndex + " to index: " + endIndex);

      if (startIndex != endIndex && moveView.requires(origCircle)) {
        textView.append("Game Over!");
      }
    }

  // private void changePlayer() {
  //   currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
  // }

}
