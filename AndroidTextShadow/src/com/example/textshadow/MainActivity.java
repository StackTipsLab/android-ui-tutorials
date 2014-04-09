package com.example.textshadow;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView textv = (TextView) findViewById(R.id.textview2);
		textv.setShadowLayer(30, 0, 0, Color.RED);

	}

}
