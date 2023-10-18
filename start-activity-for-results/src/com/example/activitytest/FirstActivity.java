package com.example.activitytest;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		Button start = (Button) findViewById(R.id.start_button);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class); 
				startActivityForResult(intent, 1); 
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if (requestCode == 1){
	    	if(resultCode == RESULT_OK){      
	    		 //here is your result
	            String result=data.getStringExtra("result");  
	            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();	            
	        }
	        if (resultCode == RESULT_CANCELED) {    
	            //Write your code if there's no result
	        	 Toast.makeText(getApplicationContext(), "Nothing Returned!", Toast.LENGTH_SHORT).show();
	        }
	    }
	}

}
