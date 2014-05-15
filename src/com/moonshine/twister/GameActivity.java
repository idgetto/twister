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

      boardView.setOnTouchListener(new OnTouchListener() {

        public boolean onTouch(View view, MotionEvent event) {

          int pointer = event.getActionIndex();
          int eventX = (int) event.getX(pointer);
          int eventY = (int) event.getY(pointer);
          int index = boardView.pointToPosition(eventX, eventY);

          if (index == -1) return true;

          TCircle circle = (TCircle) boardView.getItemAtPosition(index);


          switch (event.getActionMasked()) {

            case ACTION_DOWN:
            case ACTION_POINTER_DOWN:
                textView.append("Touched index: " + index);

                // needs --> does the newest instruction call for this circle
                if (!currentPlayer.using(index) && moveView.needs(circle)) {
                  textView.setText("Success! Index: " + index);
                  currentPlayer.press(index);
                  circle.setPlayer(currentPlayer);
                  circle.setFinger(moveView.nextFinger());
                  moveView.update();
                }
              break;

            case ACTION_UP:
            case ACTION_POINTER_UP:
                // requries --> is this circle is still necessary
                if (moveView.requires(circle))
                  textView.setText("Game Over!");
                  // gameOver();
                else {
                  currentPlayer.release(index);
                  circle.setPlayer(null);
                  circle.setFinger(Finger.NONE);
                }
              break;

          }

          return true;

        }

      });

    }

  // private void changePlayer() {
  //   currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
  // }

}
