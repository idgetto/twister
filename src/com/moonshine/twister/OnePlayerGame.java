package com.moonshine.twister;

import android.os.Bundle;
import android.content.Intent;

public class OnePlayerGame extends GameActivity {

	public final static int ROWS = MoveView.ROWS + BoardView.ROWS;
	public final static int COLS = BoardView.COLS;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_player_game);

		MoveView moveView = (MoveView) findViewById(R.id.move_view);
		currentPlayer = new TPlayer(moveView);
		update();
	}

	protected void endMove() {
		currentPlayer.incrementScore();
		update();
	}

	protected void startScores() {
		Intent scoresIntent = new Intent(this, ScoresActivity.class);
		scoresIntent.putExtra("p1Score", currentPlayer.getScore());
		startActivity(scoresIntent);
	}

}
