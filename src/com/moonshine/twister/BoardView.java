/*	
 * BoardView.java
 * Stores a grid of circles and interacts with the player
 * via an onTouchLIstener.
 * @author: Kevin You, Isaac GettoMenuActivity
 * Period: 4
 * Date: 05-22-14
 */
package com.moonshine.twister;

import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.widget.GridView;
import static android.view.MotionEvent.*;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;

import java.util.HashMap;

import android.view.MotionEvent.PointerCoords;

import java.util.ArrayList;

public class BoardView extends GridView {

	public static final int ROWS = 5;
	public static final int COLS = 4;

	private final TCircle[] circles = new TCircle[ROWS * COLS];
	private GameActivity gameActivity;
	private Context context;
	private HashMap<Integer, Integer> pointerLocs;

	/* Creates a BoardView given only context
	 * @param context Context of the BoardView
	 */
	public BoardView(Context context) {
		super(context);
		init(context);
	} 

	/* Creates a BoardView given a Context and an AttributeSet
	 * @param context Context of the BoardView
	 * @param attrs AttributeSet
	 */
	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/* Creates a BoardView given a Context, AttributeSet and style definition
	 * @param context Context of the BoardView
	 * @param attrs AttributeSet
	 * @param defStyle style 
	 */
	public BoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/* Initializes the BoardView contents, including BoardAdapter
	 * and the OnTouchListener.
	 * @param context Context of the BoardView
	 */
	private void init(Context context) {
		this.context = context;
		this.gameActivity = (GameActivity) context;
		this.pointerLocs = new HashMap<Integer, Integer>();
		setAdapter(new BoardAdapter(context, this));
	
		setOnTouchListener(new OnTouchListener() {
	
			/* Reacts to a MotionEvent
			 * @param view that the event occurs in
			 * @param event MotionEvent that contains information
			 * @return true always to confirm the touch was successful
			 */
			public boolean onTouch(View view, MotionEvent event) {
			
				int pid = event.getPointerId(event.getActionIndex());
				int circleIndex = getPointerPosition(event, pid);
	
				// touch is outside the view
				// if (circleIndex == -1) return true;
	
				TCircle circle = (TCircle) getItemAtPosition(circleIndex);
	
				switch (event.getActionMasked()) {
	
					case ACTION_DOWN:
					case ACTION_POINTER_DOWN:
	
						// save the location of this pointer
						pointerLocs.put(pid, getPointerPosition(event, pid));
	
						gameActivity.onPress(circleIndex, circle);
						break;
	
					case ACTION_UP:
					case ACTION_POINTER_UP:
			
						// remove pointer location
						pointerLocs.remove(pid);
			
						gameActivity.onRelease(circleIndex, circle);
						break;
			
					case ACTION_MOVE:
						// ACTION_MOVE can detect movements of all pointers,
						// but it doesn't know which pointer caused the event.
						// So we must do this for all pointers
						for (int pointerId : getPointerIds(event)) {
							int startCircleIndex = pointerLocs.get(pointerId);
							int endCircleIndex = getPointerPosition(event, pointerId);
			
							if (startCircleIndex == endCircleIndex) continue;
			
							// update pointer location
							if (endCircleIndex != -1)
								pointerLocs.put(pointerId, endCircleIndex);
			
							TCircle startCircle = startCircleIndex != -1 ? circles[startCircleIndex] : null;
							TCircle endCircle = endCircleIndex != -1 ? circles[endCircleIndex] : null;
			
							gameActivity.onRelease(startCircleIndex, startCircle);
							gameActivity.onPress(endCircleIndex, endCircle);
						}
						break;
			
				}
				return true;
			}
		});
	}
	
	/* Get the array of circles
	 * @return array of TCircles
	 */
	public TCircle[] getCircles() {
		return circles;
	}
	
	/* Get TCircle at index
	 * @param index 0-based index of array
	 * @return TCircle at index in circles array
	 */
	public TCircle getCircle(int index) {
		return circles[index];
	}
	
	/* Generates an ArrayList of pointerIDs from an event
	 * @param event MotionEvent with pointers
	 * @return ArrayList of pointerIDs
	 */
	private ArrayList<Integer> getPointerIds(MotionEvent event) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		int count = event.getPointerCount();
		for (int i = 0; i < count; i++) {
			int id = event.getPointerId(i);
			ids.add(id);
		}
		return ids;
	}
	
	/* Gets the positon of a pointer in an event
	 * @param event MotionEvent of pointer
	 * @param pointerId pointer ID as an int
	 * @return int position as an int
	 */
	private int getPointerPosition(MotionEvent event, int pointerId) {
		int x = (int) event.getX(event.findPointerIndex(pointerId));
		int y = (int) event.getY(event.findPointerIndex(pointerId));
		return pointToPosition(x, y);
	}
	
}
