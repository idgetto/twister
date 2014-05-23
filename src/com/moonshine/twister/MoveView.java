/*	
 * MoveView.java
 * Displays a grid of TCircles for the player
 * @author: Kevin You, Isaac GettoMenuActivity
 * Period: 4
 * Date: 05-22-14
 */

package com.moonshine.twister;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.GridView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MoveView extends GridView {

	public static final int ROWS = 1;
	public static final int COLS = 4;

	private final TCircle[] circles = new TCircle[ROWS * COLS];
	private Context context;
	private TCircle next;

	/* Creates a MoveView given a Context 
	 * @param context Context of the MoveView
	 */
	public MoveView(Context context) {
		super(context);
		init(context);
	}

	/* Creates a MoveView given a Context and AttributeSet 
	 * @param context Context of the MoveView
	 * @param attrs AttributeSet
	 */
	public MoveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/* Creates a MoveView given a Context, AttributeSet and style definition
	 * @param context Context of the MoveView
	 * @param attrs AttributeSet
	 * @param defStyle style 
	 */
	public MoveView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/* Initializes the MoveView contents, including MoveAdapter
	 * @param context Context of the MoveView
	 */
	private void init(Context context) {
		this.context = context;
		setAdapter(new MoveAdapter(context, this));
		setBackgroundColor(Color.LTGRAY);

		// do nothing (prevents blue selection)
		setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {return true;}
		});
	}

	/* Gets the array of TCircles
	 * @return array of TCircles
	 */
	public TCircle[] getCircles() {
		return circles;
	}

	/* Gets TCircle at 0-based index in array
	 * @param index 0-based position
	 * @return TCircle at index
	 */
	public TCircle getCircle(int index) {
		return circles[index];
	}

	/* Gets the Finger of the next circle
	 * @return Finger of next
	 */
	public Finger nextFinger() {
		return next.getFinger();
	}
	
	/* Gets the Color of the next circle
	 * @return Color of next
	 */
	public TColor nextColor() {
		return next.getColor();
	}

	/* Gets next TCircle
	 * @return TCircleof next move
	 */
	public TCircle nextCircle() {
		return next;
	}

	/* Generates a new random move
	 */
	public void update() {
		int index = (int) (Math.random() * circles.length);

		TColor color = TColor.random();
		Finger finger = Finger.get(index);

		if (circles[index] == null) {
			circles[index] = new TCircle(color, finger, context);
		}
		else {
			TCircle circle = circles[index];
			circle.setColor(color);
			circle.setFinger(finger);
		}

		next = circles[index];
		next.glow();

		invalidateViews();
	}

	/* Checks if the latest move satisfies next
	 * @param circle TCircle being checked
	 * @return true if circle is needed, false if not needed
	 */
	public boolean needs(TCircle circle) {
		return circle.getColor() == next.getColor();
	}

	/* Checks if a circle that is lifted up is still needed
	 * @param circle TCircle that is being checked
	 * @return true if required, false if not required
	 */
	public boolean requires(TCircle circle) {
		Finger finger = circle.getFinger();

		// accidental tap on unused circle
		if (finger == Finger.NONE) return false;

		TCircle required = circles[finger.getId()];

		// check for same color twice
		// in which case you are allowed to release the color
		if (required == next)
			return false;

		return circle.getColor() == required.getColor();
	}

	/* Ends the move, reseting next
	 */
	public void endMove() {
		next.fade();
		next = null;
		invalidateViews();
	}

}
