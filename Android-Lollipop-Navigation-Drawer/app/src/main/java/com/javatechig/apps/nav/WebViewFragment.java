package com.javatechig.apps.nav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebViewFragment extends Fragment {

    public static Fragment getInstance(Bundle bundle) {
        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* Getting url passed as bundle data */
        String url = getArguments().getString("url-key");
        Log.e("WebView", url);

		/* Creating view corresponding to the fragment */
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

		/* Initialising and loading url in WebView */
        WebView webView = (WebView) view.findViewById(R.id.webView);
        webView.loadUrl(url);

        return view;

    }
}