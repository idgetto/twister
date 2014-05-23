package com.moonshine.twister;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.content.Intent;
import java.util.TreeSet;
import android.content.SharedPreferences;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;

public class ScoresActivity extends BaseActivity {

  private static final String GAME_OVER = "Game Over!";
  private static final String HIGH_SCORES = "High Scores";  
  private static final String DATA = "com.moonshine.twister.DATA";
  private static final int SCORE_LENGTH = 5;

  // resultView displays the outcome (winner) while scoreView displays scores of each player
  private TextView resultView;
  private TextView scoreView;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scores_activity);

    resultView = (TextView) findViewById(R.id.result_view);
    scoreView = (TextView) findViewById(R.id.score_view);

    Intent intent = getIntent();
    int p1Score = intent.getIntExtra(EXTRA_P1_SCORE, -1);
    int p2Score = intent.getIntExtra(EXTRA_P2_SCORE, -1);
    String winner = intent.getStringExtra(EXTRA_WINNER);

    SharedPreferences sp = getSharedPreferences(DATA, MODE_PRIVATE);
    TreeSet<Integer> scores = getHighScores(sp);

    // Checks if ScoreActivity was called when the game ended
    if (p1Score != -1) {
      // If winner is null, the the game was single player
      if (winner == null) {
        resultView.setText(GAME_OVER);

        scoreView.setText(PLAYER_ONE_NAME + " score: " + p1Score);
        scores.add(p1Score);

        scoreView.append("\n\n" + HIGH_SCORES + ":\n");
        displayScores(scores);
      }
      else {
        resultView.setText(winner + " Wins!");

        scoreView.setText(PLAYER_ONE_NAME + " score: " + p1Score);
        scores.add(p1Score);

        scoreView.append(PLAYER_TWO_NAME + " score: " + p2Score);
        scores.add(p2Score);

        scoreView.append("\n\n" + HIGH_SCORES + ":\n");
        displayScores(scores);
      }

      SharedPreferences.Editor editor = sp.edit();
      saveScores(scores, editor);

    }
    // If from main menu
    else {
      resultView.setText(HIGH_SCORES);
      displayScores(scores);
    }

  }

  private TreeSet<Integer> getHighScores(SharedPreferences sp) {
    Map<String, ?> scoreMap = sp.getAll();
    TreeSet<Integer> scores = new TreeSet<Integer>();
    for (Object o : scoreMap.values()) {
      scores.add((Integer) o);
    }
    return scores;
  }

  private void saveScores(TreeSet<Integer> scores,  SharedPreferences.Editor editor) {
    int score = Integer.MAX_VALUE;
    for (int i = 0; i < scores.size() && i < SCORE_LENGTH; i++) {
      score = scores.lower(score);
      editor.putInt("score-" + (i + 1), score);
    }
    editor.apply();
  }

  private void displayScores(TreeSet<Integer> scores) {
    int score = Integer.MAX_VALUE;
    for (int i = 0; i < scores.size() && i < SCORE_LENGTH; i++) {
      scoreView.append("\n" + (i + 1) + ". ");
      score = scores.lower(score);
      String points = score == 1 ? "point" : "points";
      scoreView.append(score + " " + points);
    }
  }

}
