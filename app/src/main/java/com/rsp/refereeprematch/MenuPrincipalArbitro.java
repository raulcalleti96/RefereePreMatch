package com.rsp.refereeprematch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalArbitro extends AppCompatActivity {



    private Button ajustesbtn, cuentabtn, informebtn,recibobtn,partidobtn,newbtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipalarbitro);

        partidobtn = findViewById(R.id.partidosbtn);
        recibobtn = findViewById(R.id.recibobtn);
        cuentabtn = findViewById(R.id.cuentabtn);
        ajustesbtn = findViewById(R.id.ajustesbtn);
        informebtn = findViewById(R.id.informebtn);
        newbtn = findViewById(R.id.btnnvarb);

        ajustesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Ajustes.class);
                startActivity(intent);
            }
        });
        partidobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Partidos.class);

                startActivity(intent);
            }
        });
        recibobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Recibos.class);
                startActivity(intent);
            }
        });
        cuentabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, InfoContacto.class);
                startActivity(intent);
            }
        });
        informebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MenuPrincipalArbitro.this, Informes.class);
                startActivity(intent);
            }
        });

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuPrincipalArbitro.this);
                View mView = getLayoutInflater().inflate(R.layout.pruebaspinner,null);
                mBuilder.setTitle("Crear nuevo");
                Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MenuPrincipalArbitro.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.crearnuevoArbitro));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Informe")){

                            Intent intent = new Intent (MenuPrincipalArbitro.this, NuevoInforme.class);
                            startActivity(intent);
                        }
                    }
                });

                mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dial = mBuilder.create();
                dial.show();
            }
        });

    }


}
