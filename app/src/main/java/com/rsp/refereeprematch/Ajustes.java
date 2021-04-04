package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Ajustes extends AppCompatActivity
{
    private Button sesionbtn;
    private Button buttonReadme;
    private Button buttonLicence;
    private Button buttonPriva;
    private Button buttonVolver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);


        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Español", "Euskera", "English","Français", "Deutch"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

        buttonVolver = findViewById(R.id.botonvolver);
        sesionbtn = findViewById(R.id.botonsesion);
        buttonReadme =  findViewById(R.id.buttonreadme);
        buttonLicence =  findViewById(R.id.buttonlicence);
        buttonPriva =  findViewById(R.id.buttonpriva);

        sesionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (Ajustes.this, MenuPrincipalArbitro.class);
                startActivity(intent);
            }
        });

        sesionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (Ajustes.this, Login.class);
                startActivity(intent);
            }
        });
        buttonReadme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (Ajustes.this, Acercade.class);
                startActivity(intent);
            }
        });
        buttonLicence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (Ajustes.this, Licencia.class);
                startActivity(intent);
            }
        });
        buttonPriva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (Ajustes.this, Privacidad.class);
                startActivity(intent);
            }
        });


    }
}
