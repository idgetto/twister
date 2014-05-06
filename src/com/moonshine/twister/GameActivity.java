package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.Toast;
import android.content.Context;

public class GameActivity extends Activity {

  private BoardView boardView;
  private MoveView moveView;
  private TextView textView;

  private Player currentPlayer;
  private Player playerOne;
  private Player playerTwo;

  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.game_activity);

      boardView = (BoardView) findViewById(R.id.board_view);
      moveView  = (MoveView)  findViewById(R.id.move_view);
      textView  = (TextView)  findViewById(R.id.text_view);

      boardView.setAdapter(new BoardAdapter(this));

      final Context context = this;
      boardView.setOnItemClickListener(new OnItemClickListener() {

        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
          Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
        }

      });

      playerOne = new Player();
      playerTwo = new Player();
      currentPlayer = playerOne;

    }

  private void changePlayer() {
    currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
  }

}
