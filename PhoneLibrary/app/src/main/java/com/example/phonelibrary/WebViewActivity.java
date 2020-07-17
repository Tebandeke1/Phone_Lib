package com.example.phonelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.webview);

        Intent intent = getIntent();

        //make sure the intent  is not empty
        if (null != intent){
            String str = intent.getStringExtra("url");
            webView.loadUrl(str);

            //this helps to open the website in our application other than using another browser
            webView.setWebViewClient(new WebViewClient());

        }


    }

    //this  method helps on action bar items or menu  items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //this helps a user to go back to the previous activity

    @Override
    public void onBackPressed() {
        //here we are checking if we can go back to the previous page
        //or check whether we have a previous page
        if (webView.canGoBack()){
            //if we have then we should go back to that page
            webView.goBack();
        }else {
            //or we just go back to the main activity
            super.onBackPressed();
        }
    }

}
