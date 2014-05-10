package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.GridView;

public class GameActivity extends Activity {

  private BoardView boardView;
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

      System.out.println("Just before findViewById");
      boardView = (BoardView) findViewById(R.id.board_view);
      moveView  = (GridView) findViewById(R.id.move_view);
      textView  = (TextView) findViewById(R.id.text_view);

      playerOne = new Player();
      playerTwo = new Player();
      currentPlayer = playerOne;

    }

  private void changePlayer() {
    currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
  }

}
