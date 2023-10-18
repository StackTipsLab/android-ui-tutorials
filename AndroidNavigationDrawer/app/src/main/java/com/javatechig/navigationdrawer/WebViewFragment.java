package com.javatechig.navigationdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		/* Retrieving the currently selected item number */
        String title = getArguments().getString("title");

        /* Updating the action bar title */
        getActivity().getActionBar().setTitle(title);

        /* Getting the WebView target url */
        String url = getArguments().getString("url");

		/* Creating view corresponding to the fragment */
		View v = inflater.inflate(R.layout.fragment_layout, container, false);

		/* Initializing and loading url in WebView */
		WebView webView = (WebView)v.findViewById(R.id.webView); 
		webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);
		return v;
	}

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}