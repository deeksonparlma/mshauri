package com.epicodus.mshauri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.mshauri.maps.MapsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class postToForumActivity extends AppCompatActivity implements  View.OnClickListener{
    @BindView(R.id.webview) WebView myWebView;
    @BindView(R.id.foundation1)
    ImageView mImage;
    @BindView(R.id.forumOption) ImageView mForum;
    @BindView(R.id.foundation2)
    TextView mText;
    @BindView(R.id.donate) ImageView mDonate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_to_forum);
        ButterKnife.bind(this);
        mImage.setOnClickListener(this);
        mText.setOnClickListener(this);
        mDonate.setOnClickListener(this);
        mForum.setOnClickListener(this);
        String url = "http://mshauri-connect.herokuapp.com/forums/";
         myWebView.setWebViewClient(new MyBrowser());
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        if(v == mImage || v ==mText){
            Intent intent = new Intent(postToForumActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        else if(v==mDonate){
            Intent intent = new Intent(postToForumActivity.this, DonationsActivity.class);
            startActivity(intent);
        }
        else if(v==mForum){
            Intent intent = new Intent(postToForumActivity.this, homeActivity.class);
            startActivity(intent);
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
