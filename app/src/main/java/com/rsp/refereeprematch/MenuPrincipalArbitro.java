package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalArbitro extends AppCompatActivity {



    private Button ajustesbtn, cuentabtn, informebtn,recibobtn,partidobtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipalarbitro);

        partidobtn = findViewById(R.id.partidosbtn);
        recibobtn = findViewById(R.id.recibobtn);
        cuentabtn = findViewById(R.id.cuentabtn);
        ajustesbtn = findViewById(R.id.ajustesbtn);
        informebtn = findViewById(R.id.informebtn);

        ajustesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Ajustes.class);
                Bundle b = new Bundle();
                b.putInt("id", 0);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        partidobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Partidos.class);
                Bundle b = new Bundle();
                b.putInt("id", 0);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        recibobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Recibos.class);
                Bundle b = new Bundle();
                b.putInt("id", 0);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        cuentabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Arbitro.class);
                Bundle b = new Bundle();
                b.putInt("id", 0);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        informebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Informes.class);
                Bundle b = new Bundle();
                b.putInt("id", 0);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }


}
