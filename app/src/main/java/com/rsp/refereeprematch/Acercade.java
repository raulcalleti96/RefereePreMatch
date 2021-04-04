package com.rsp.refereeprematch;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Acercade extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://github.com/raulcalleti96/RefereePreMatch/blob/main/README.md");
    }
}
