package com.moonshine.twister;

import android.os.Bundle;
import android.content.Intent;

public class TwoPlayerGame extends GameActivity {

	public final static int ROWS = MoveView.ROWS + BoardView.ROWS + MoveView.ROWS;
	public final static int COLS = BoardView.COLS;

	private TPlayer playerOne;
	private TPlayer playerTwo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_player_game);

		MoveView moveViewOne = (MoveView) findViewById(R.id.move_view_one);
		MoveView moveViewTwo = (MoveView) findViewById(R.id.move_view_two);
		playerOne = new TPlayer(moveViewOne);
		playerTwo = new TPlayer(moveViewTwo);

		currentPlayer = playerOne;
		update();
	}

	protected void endMove() {
		currentPlayer.incrementScore();
		currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
		update();
	}

	protected void startScores() {
		Intent scoresIntent = new Intent(this, ScoresActivity.class);
		scoresIntent.putExtra("p1Score", playerOne.getScore());
		scoresIntent.putExtra("p2Score", playerTwo.getScore());
		startActivity(scoresIntent);
	}

}