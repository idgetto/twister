package com.moonshine.twister;

import android.os.Bundle;
import android.content.Intent;

public class OnePlayerGame extends GameActivity {
	
	private TPlayer playerOne;
	private TPlayer playerTwo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_player_game);
		
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
		scoresIntent.putIntExtra("p1Score", playerOne.getScore());
		scoresIntent.putIntExtra("p2Score", playerTwo.getScore());
		startActivity(scoresIntent);
	}

}