package com.moonshine.twister;

import android.os.Bundle;
import android.content.Intent;

public class OnePlayerGame extends GameActivity {
	
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
		scoresIntent.putIntExtra("p1Score", currentPlayer.getScore());
		startActivity(scoresIntent);
	}

}