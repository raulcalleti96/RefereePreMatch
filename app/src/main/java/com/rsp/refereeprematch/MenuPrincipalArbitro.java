package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MenuPrincipalArbitro extends AppCompatActivity {



    private Button ajustesbtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipalarbitro);

        ajustesbtn = findViewById(R.id.ajustesbtn);

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

    }


}
