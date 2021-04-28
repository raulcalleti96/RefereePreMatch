package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalDelegado extends AppCompatActivity {


    private Button ajustesbtn, arbitrosbtn, estadiobtn,recibobtn,partidobtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipaldelegado);

        partidobtn = findViewById(R.id.partidosbtn);
        recibobtn = findViewById(R.id.recibobtn);
        arbitrosbtn = findViewById(R.id.arbitrosbtn);
        ajustesbtn = findViewById(R.id.ajustesbtn);
        estadiobtn = findViewById(R.id.estadiobtn);

        ajustesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalDelegado.this, Ajustes.class);
                Bundle b = new Bundle();
                b.putInt("id", 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        arbitrosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalDelegado.this, Arbitros.class);
                Bundle b = new Bundle();
                b.putInt("id", 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        estadiobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalDelegado.this, Estadios.class);
                Bundle b = new Bundle();
                b.putInt("id", 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        partidobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalDelegado.this, Partidos.class);
                Bundle b = new Bundle();
                b.putInt("id", 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        recibobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalDelegado.this, Recibos.class);
                Bundle b = new Bundle();
                b.putInt("id", 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}