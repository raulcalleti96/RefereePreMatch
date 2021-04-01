package com.rsp.refereeprematch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView titulotv;
    private TextView createdtv;
    private ImageView logotv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulotv = (TextView) findViewById(R.id.titulotv);
        createdtv = (TextView) findViewById(R.id.createdtv);
        logotv = (ImageView) findViewById(R.id.logoiv);
        Animation animacion = AnimationUtils.loadAnimation(this,R.anim.transicion);
        titulotv.startAnimation(animacion);
        createdtv.startAnimation(animacion);
        logotv.startAnimation(animacion);
    }
}