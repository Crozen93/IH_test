package com.example.smk.WebView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Ihor on 10.08.2016.
 */
public class SimpleWebViewClient extends WebViewClient {

    private Activity activity = null;


    public SimpleWebViewClient(Activity activity) {
        this.activity = activity;

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {


        // все ссылки, в которых содержится 'smktesting.herokuapp.com'
        // будут открываться внутри приложения
        if (url.contains("smktesting.herokuapp.com"))
        {
            return false;
        }

        // все остальные ссылки будут спрашивать какой браузер открывать
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }
}

