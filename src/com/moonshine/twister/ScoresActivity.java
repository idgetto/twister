package com.moonshine.twister;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.content.Intent;

public class ScoresActivity extends Activity {

  private TableLayout table;
  private boolean singlePlayer;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scores_activity);

    TextView rView = (TextView) findViewById(R.id.result_view);
    TextView sView = (TextView) findViewById(R.id.score_view);

    Intent intent = getIntent();
    int p1Score = intent.getIntExtra("p1Score", -1);
    int p2Score = intent.getIntExtra("p2Score", -1);
    String result = intent.getStringExtra("result");
    
    if (result == null && p1Score != -1)
    	rView.setText("Game Over!");
    else if (result != null)
    	rView.setText(result + " Wins!");
    else
    	rView.setText("High Scores");

    if (p1Score != -1)
    	sView.setText("P1 score: " + p1Score);
    if (p2Score != -1)
    	sView.append("\nP2 score: " + p2Score);
    // TreeMap<String, Integer> scores = SharedPreferences.getAll();
    // for (Map.Entry<String, Integer> score : scores) {
  }

}
