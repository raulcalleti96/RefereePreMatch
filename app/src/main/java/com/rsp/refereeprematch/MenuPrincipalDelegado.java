package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalDelegado extends AppCompatActivity {


    private Button ajustesbtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipaldelegado);

        ajustesbtn = findViewById(R.id.ajustesbtn);

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
    }
}