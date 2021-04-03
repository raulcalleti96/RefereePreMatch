package com.rsp.refereeprematch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView titulotv;
    private TextView createdtv;
    private ImageView logotv;
    private static int TIEMPO = 3000; //Time to launch the another activity
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);

            }
        }, TIEMPO);
    }
}