package com.rsp.refereeprematch;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Licencia extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licencia);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://github.com/raulcalleti96/RefereePreMatch/blob/main/LICENSE");
    }
}
