package com.rsp.refereeprematch;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Ajustes extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);


        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Español", "Euskera", "English","Français", "Deutch"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
    }
}
