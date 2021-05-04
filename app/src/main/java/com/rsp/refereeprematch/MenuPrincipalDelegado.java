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

public class MenuPrincipalDelegado extends AppCompatActivity {


    private Button ajustesbtn, arbitrosbtn, estadiobtn,recibobtn,partidobtn,newbtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipaldelegado);

        partidobtn = findViewById(R.id.partidosbtn);
        recibobtn = findViewById(R.id.recibobtn);
        arbitrosbtn = findViewById(R.id.arbitrosbtn);
        ajustesbtn = findViewById(R.id.ajustesbtn);
        estadiobtn = findViewById(R.id.estadiobtn);
        newbtn = findViewById(R.id.newbtn);

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
        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuPrincipalDelegado.this);
                View mView = getLayoutInflater().inflate(R.layout.pruebaspinner,null);
                mBuilder.setTitle("Crear nuevo");
                Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MenuPrincipalDelegado.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.crearnuevoDelegado));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Arbitro")){

                            Intent intent = new Intent (MenuPrincipalDelegado.this, NuevoContacto.class);
                            Bundle b = new Bundle();
                            b.putInt("id", 1);
                            intent.putExtras(b);
                            startActivity(intent);

                        }else if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Estadio")){
                            Intent intent = new Intent (MenuPrincipalDelegado.this, NuevoEstadio.class);
                            Bundle b = new Bundle();
                            b.putInt("id", 1);
                            intent.putExtras(b);
                            startActivity(intent);

                        }else if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Recibo")){
                            Intent intent = new Intent (MenuPrincipalDelegado.this, NuevoRecibo.class);
                            Bundle b = new Bundle();
                            b.putInt("id", 1);
                            intent.putExtras(b);
                            startActivity(intent);

                        }else if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("Partido")){
                            Intent intent = new Intent (MenuPrincipalDelegado.this, NuevoPartido.class);
                            Bundle b = new Bundle();
                            b.putInt("id", 1);
                            intent.putExtras(b);
                            startActivity(intent);

                        }
                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
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