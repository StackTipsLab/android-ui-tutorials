package com.javatechig;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsListActivity extends Activity {

	private NewsListAdapter adapter;
	
	public static final String KEY_HEADLINE="news_headline";
	public static final String KEY_DETAILS="news_details";
	public static final String KEY_PUBDATE = "news_pub_date";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_list);
		
		adapter = new NewsListAdapter(this, getData());
		ListView list = (ListView) findViewById(R.id.newsList);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
				NewsItem item = (NewsItem) adapter.getItem(position);
				
				Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
				intent.putExtra(KEY_HEADLINE, item.getHeadline());
				intent.putExtra(KEY_PUBDATE, item.getPubDate());
				intent.putExtra(KEY_DETAILS, item.getDetails());
				
				
				startActivity(intent); 
			}
		});
	}

	private ArrayList<NewsItem> getData() {
		ArrayList<NewsItem> newsList = new ArrayList<NewsItem>();
		String[] headlines = getResources().getStringArray(R.array.news_headlines);
		String[] pubDate = getResources().getStringArray(R.array.news_pubdate);
		String[] details = getResources().getStringArray(R.array.news_details);

		for (int i = 0; i < headlines.length; i++) {
			NewsItem item = new NewsItem();
			item.setHeadline(headlines[i]);
			item.setPubDate(pubDate[i]);
			item.setDetails(details[i]);
			newsList.add(item);
		}
		return newsList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
