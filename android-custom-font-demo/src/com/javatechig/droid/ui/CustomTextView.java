package com.javatechig.droid.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

	public CustomTextView(Context context) {
		super(context);
		setFont();
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFont();
	}

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFont();
	}

	private void setFont() {
		Typeface font = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Maximum.ttf");
		setTypeface(font, Typeface.NORMAL);
	}
}