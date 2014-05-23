/*	
 * BaseActivity.java
 * A superclass Activity over all the other Activities, to share constants.
 * @author: Kevin You, Isaac Getto
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

import android.app.Activity;

public abstract class BaseActivity extends Activity {

	public static final String EXTRA_P1_SCORE = "com.moonshine.twister.EXTRA_P1_SCORE";
	public static final String EXTRA_P2_SCORE = "com.moonshine.twister.EXTRA_P2_SCORE";
    public static final String EXTRA_WINNER = "com.moonshine.twister.EXTRA_WINNER";

    public static final String PLAYER_ONE_NAME = "Player One";
    public static final String PLAYER_TWO_NAME = "Player Two";

}
