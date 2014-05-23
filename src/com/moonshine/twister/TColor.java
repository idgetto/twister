package com.moonshine.twister;

public enum TColor {
	GREEN(0),
	YELLOW(1),
	BLUE(2),
	RED(3);

	private int id;

	TColor(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static TColor random() {
		TColor[] colors = TColor.values();
		TColor color = colors[(int) (Math.random() * colors.length)];
		return color;
	}

}
