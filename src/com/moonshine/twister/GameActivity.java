package com.moonshine.twister;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class GameActivity extends Activity {

  private BoardView mBoardView;
  private MoveView mMoveView;
  private TextView mTextView;

  private Player currentPlayer;
  private Player playerOne;
  private Player playerTwo;

  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.game_activity);

      // mBoardView = (BoardView) findViewById(R.id.board_view);
      // mMoveView  = (MoveView)  findViewById(R.id.move_view);
      // mTextView  = (TextView)  findViewById(R.id.text_view);

      // mBoardView.setAdapter(new BoardAdapter(this));

      // mBoardView.setOnItemClickListener(new OnItemClickListener() {

        // public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //   Toast.makeText(HelloGridView.this, "" + position, Toast.LENGTH_SHORT).show();
        // }

      // });

      playerOne = new Player();
      playerTwo = new Player();
      currentPlayer = playerOne;

    }

  private void changePlayer() {
    currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
  }

}
