package com.javatechig;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {

	private ArrayList<NewsItem> listData;
	private LayoutInflater layoutInflater;
	private Context context;
	
	public NewsListAdapter(Context context, ArrayList<NewsItem> listData) {
		this.listData = listData;
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.snippet_news_list_row, null);
			holder = new ViewHolder();
			holder.headline = (TextView) convertView.findViewById(R.id.newsHeadline);
			holder.pubDate = (TextView) convertView.findViewById(R.id.pubDate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.headline.setText(listData.get(position).getHeadline());
		holder.pubDate.setText(listData.get(position).getPubDate());

		
		if (position % 2 == 1) {
			convertView.setBackgroundColor(context.getResources().getColor(R.color.list_row_color1));  
		} else {
			convertView.setBackgroundColor(context.getResources().getColor(R.color.list_row_color2));  
		}
		
		return convertView;
	}

	static class ViewHolder {
		TextView headline;
		TextView pubDate;
	}

}