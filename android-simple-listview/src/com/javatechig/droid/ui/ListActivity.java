package com.javatechig.droid.ui;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {

	// Initialize the array
	String[] monthsArray = { "JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC" };

	// Declare the UI components
	private ListView monthsListView;

	private ArrayAdapter arrayAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		// Initialize the UI components
		monthsListView = (ListView) findViewById(R.id.months_list);
		// For this moment, you have ListView where you can display a list.
		// But how can we put this data set to the list?
		// This is where you need an Adapter

		// context - The current context.
		// resource - The resource ID for a layout file containing a layout to use when instantiating views.
		// From the third parameter, you plugged the data set to adapter
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthsArray);

		// By using setAdapter method, you plugged the ListView with adapter
		monthsListView.setAdapter(arrayAdapter);
	}
}