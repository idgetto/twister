

package com.moonshine.twister;

import android.os.Bundle;
import android.content.Intent;

public class OnePlayerGame extends GameActivity {

	public final static int ROWS = MoveView.ROWS + BoardView.ROWS;
	public final static int COLS = BoardView.COLS;

	/* Called when the activity is first created. Sets currentPlayer.
	 * One MoveView is created for the current player.
	 * @param savedInstanceState Bundle of saved instance state 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_player_game);

		MoveView moveView = (MoveView) findViewById(R.id.move_view);
		currentPlayer = new TPlayer(moveView);
		update();
	}

	/* Ends the current player's move, incremenitng score.
	 */
	protected void endMove() {
		currentPlayer.incrementScore();
		update();
	}

	/* Starts scoreActivity for one player
	 * @param loser TPlayer that caused the loss
	 */
	protected void startScores(TPlayer loser) {
		Intent scoresIntent = new Intent(this, ScoresActivity.class);
		scoresIntent.putExtra(EXTRA_P1_SCORE, currentPlayer.getScore());
		startActivity(scoresIntent);
	}

}
