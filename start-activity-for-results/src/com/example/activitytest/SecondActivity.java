package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Button returnResult = (Button) findViewById(R.id.return_button);
		returnResult.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// returing result back
				Intent resultIntent = new Intent();
				resultIntent.putExtra("result", "Getting Smile Back!!");
				setResult(RESULT_OK, resultIntent);
				finish();

				// if you don't want to return any result
				// setResult(RESULT_CANCELED, resultIntent);
			}
		});

		Button back = (Button) findViewById(R.id.cancel_button);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// if you don't want to return any result
				Intent resultIntent = new Intent();
				setResult(RESULT_CANCELED, resultIntent);
				finish();
			}
		});
	}
}
