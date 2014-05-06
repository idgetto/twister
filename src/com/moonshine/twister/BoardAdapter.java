package com.moonshine.twister;

public class BoardAdapter extends BaseAdapter {

	private final ROWS = 6;
	private final COLS = 4;

    TODO: add circle images
    private final int[] circleImgs = {  R.drawable.green_circle,
                                        R.drawable.yellow_circle,
                                        R.drawable.blue_circle,
                                        R.drawable.red_circle }

	private Context mContext;
	private Circle[] circles;


	public BoardAdapter(Context context) {

		mContext = context;
        circles = new Circle[ROWS * COLS];
        circleImgs =

        for (int i = 0; i < circles.length; i++) {
            int imageId = circleImgs[i % 4];
            circles[i] = new Circle(imageId, Finger.NONE, this);
        }

	}

	public int getCount() {
        return circles.length;
    }

    public Circle getItem(int position) {
        return circles[position];
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
