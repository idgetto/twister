package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.AdapterView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import android.content.Context;
import android.view.MotionEvent;
import static android.view.MotionEvent.*;
import android.widget.GridView;

public class GameActivity extends Activity {

  private GridView boardView;
  private GridView moveView;
  private TextView textView;

  private Player currentPlayer;
  private Player playerOne;
  private Player playerTwo;

  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.game_activity);

      boardView = (GridView) findViewById(R.id.board_view);
      moveView  = (GridView) findViewById(R.id.move_view);
      textView  = (TextView) findViewById(R.id.text_view);

      playerOne = new Player();
      playerTwo = new Player();
      currentPlayer = playerOne;

      boardView.setAdapter(new BoardAdapter(this, boardView));

      final Context context = this;

      boardView.setOnTouchListener(new OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {

          int eventX = (int) event.getX();
          int eventY = (int) event.getY();
          int index = boardView.pointToPosition(eventX, eventY);
          Circle circle = (Circle) boardView.getItemAtPosition(index);
          System.out.println("Event!!!" + index + " " + event.getAction());

          switch (event.getAction()) {

            case ACTION_DOWN:
                // Toast.makeText(context, "Touched circle at index: " + index, Toast.LENGTH_SHORT).show();
              textView.setText("Touched circle at index: " + index);
              break;

            case ACTION_UP:
                // Toast.makeText(context, "Let go of circle at index: " + index, Toast.LENGTH_SHORT).show();
              textView.setText("Let go of circle at index: " + index);
              break;

          }

          return true;

        }

      });

    }

  private void changePlayer() {
    currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
  }

}
