package com.javatechig.customizedlist;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.customizedlist.R;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<NewsItem> image_details = getListData();
		final ListView lv1 = (ListView) findViewById(R.id.custom_list);
		lv1.setAdapter(new CustomListAdapter(this, image_details));
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object o = lv1.getItemAtPosition(position);
				NewsItem newsData = (NewsItem) o;
				Toast.makeText(MainActivity.this, "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
			}

		});

	}

	private ArrayList<NewsItem> getListData() {
		ArrayList<NewsItem> results = new ArrayList<NewsItem>();
		NewsItem newsData = new NewsItem();
		newsData.setHeadline("Dance of Democracy");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Major Naxal attacks in the past");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("BCCI suspends Gurunath pending inquiry ");
		newsData.setReporterName("Rajiv Chandan");
		newsData.setDate("May 26, 2013, 13:35");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Life convict can`t claim freedom after 14 yrs: SC");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Indian Army refuses to share info on soldiers mutilated at LoC");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("French soldier stabbed; link to Woolwich attack being probed");
		newsData.setReporterName("Sudeep Nanda");
		newsData.setDate("May 26, 2013, 13:35");
		results.add(newsData);

		return results;
	}
}
