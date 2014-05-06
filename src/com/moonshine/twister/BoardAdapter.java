package com.moonshine.twister;

import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.content.Context;

public class BoardAdapter extends BaseAdapter {

	private final int ROWS = 6;
	private final int COLS = 4;

	private Context mContext;
	private Circle[] circles;

	public BoardAdapter(Context context) {

		mContext = context;
        circles = new Circle[ROWS * COLS];

        for (int i = 0; i < circles.length; i++) {
            int colorIndex = i % COLS;
            circles[i] = new Circle(colorIndex, Finger.NONE, mContext);
        }

	}

	public int getCount() {
        return circles.length;
    }

    public Circle getItem(int position) {
        return circles[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

		Circle circle = circles[position];
        imageView.setImageResource(circle.getImageId());
        return imageView;
    }


}
