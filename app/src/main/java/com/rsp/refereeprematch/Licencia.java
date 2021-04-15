package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Licencia extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licencia);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://github.com/raulcalleti96/SanBiblioteca/blob/master/LICENSE.md");

    }
}
