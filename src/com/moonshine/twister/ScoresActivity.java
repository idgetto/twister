package com.moonshine.twister;

import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.FrameLayout;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.view.View;
import android.content.Context;
import android.content.Intent;

public class ScoresActivity extends Activity {

  private TableLayout table;
  private boolean singlePlayer;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scores_activity);

    TextView tv = (TextView) findViewById(R.id.text_view);

    Intent intent = getIntent();
    int p1Score = intent.getIntExtra("p1score", -1);
    int p2Score = intent.getIntExtra("p2score", -1);

    if (p1Score != -1)
      tv.setText("P1 score: " + p1Score);
    if (p2Score != -1)
      tv.append("P2 score: " + p2Score);
    // TreeMap<String, Integer> scores = SharedPreferences.getAll();
    // for (Map.Entry<String, Integer> score : scores) {
  }

}
