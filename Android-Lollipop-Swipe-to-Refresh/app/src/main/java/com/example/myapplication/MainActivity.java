package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends ActionBarActivity {

    private ListView mListView = null;
    private ArrayAdapter mAdapter = null;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;

    private String[] feedList = null;
    private String feedUrl = "http://javatechig.com/api/get_category_posts/?dev=1&slug=android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        //Start Downloading data
        new DownloadFilesTask().execute(feedUrl);

        //Initialize swipe to refresh view
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refreshing data on server
                new DownloadFilesTask().execute(feedUrl);
            }
        });
    }


    private void updateList() {
        ArrayAdapter mAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, feedList);
        mListView.setAdapter(mAdapter);

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    private class DownloadFilesTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPostExecute(Void result) {
            if (null != feedList) {
                updateList();
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            // getting JSON string from URL
            JSONObject json = getJSONFromUrl(params[0]);

            //parsing json data
            parseJson(json);
            return null;
        }
    }

    public JSONObject getJSONFromUrl(String url) {
        InputStream is = null;
        JSONObject jObj = null;
        String json = null;

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();

            jObj = new JSONObject(json);

        } catch (Exception e) {
            Log.e("Error", "Error parsing data " + e.toString());
        }
        return jObj;
    }


    public void parseJson(JSONObject json) {
        try {
            if (json.getString("status").equalsIgnoreCase("ok")) {
                JSONArray posts = json.getJSONArray("posts");

                feedList = new String[posts.length()];
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = (JSONObject) posts.getJSONObject(i);
                    feedList[i] = post.getString("title");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
