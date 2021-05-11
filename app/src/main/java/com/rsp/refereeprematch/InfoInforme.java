package com.rsp.refereeprematch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class InfoInforme extends AppCompatActivity {

    TextView local, visitante, competicion, fecha, estadio, recibo,arbitro,asistente1,asistente2,capitanlocal, capitanvisitante,entrenadorlocal,entrenadorvisitante;
    TextView goleadorlocal, goleadorvisitante,conflictivolocal, conflictivovisitante;
    Button botoneliminar,botonVolver;
    String nombre;
    int id;
    FirebaseFirestore db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoinforme);
        Bundle b = getIntent().getExtras();
        nombre = b.getString("nombre");
        id = b.getInt("id");
        db = FirebaseFirestore.getInstance();
        botonVolver = findViewById(R.id.volverbtnpartido);
        botoneliminar = findViewById(R.id.guardarnuevocontacto);
        local = findViewById(R.id.localnuevotvi);
        visitante = findViewById(R.id.equipovisitantetv);
        competicion = findViewById(R.id.competiciontv);
        fecha = findViewById(R.id.fechatv);
        estadio = findViewById(R.id.estadiotv);
        recibo = findViewById(R.id.recibotv);
        arbitro = findViewById(R.id.arbitrotv);
        asistente1 = findViewById(R.id.asistente1tv);
        asistente2 = findViewById(R.id.asistente2tv);
        capitanlocal = findViewById(R.id.CapitanLi);
        capitanvisitante = findViewById(R.id.CapitanVi);
        entrenadorlocal = findViewById(R.id.entrenadorlocal);
        entrenadorvisitante = findViewById(R.id.entrenadorvisitantei);
        goleadorlocal = findViewById(R.id.Goleadorlocali);
        goleadorvisitante = findViewById(R.id.GoleadorVisitantei);
        conflictivolocal = findViewById(R.id.ConflictivoLi);
        conflictivovisitante = findViewById(R.id.ConflictivoVi);
        rellenaDatosInforme();

        if(id == 0) {
            botoneliminar.setVisibility(View.GONE);
        }else{
            botoneliminar.setVisibility(View.VISIBLE);
        }
    }

    public void rellenaDatosInforme() {

        db.collection("Informes").document(nombre).get().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {

                DocumentSnapshot document = task1.getResult();

                if (document.exists()) {

                    local.setText((String) document.get("EquipoLocal"));
                    visitante.setText((String) document.get("EquipoVisitante"));
                    competicion.setText((String) document.get("Competicion"));
                    fecha.setText((String) document.get("Fecha"));
                    estadio.setText((String) document.get("Estadio"));
                    recibo.setText((String) document.get("Recibo"));
                    arbitro.setText((String) document.get("ArbitroPrincipal"));
                    asistente1.setText((String) document.get("Asistente1"));
                    asistente2.setText((String) document.get("Asistente2"));

                    capitanlocal.setText((String) document.get("CapitanLocal"));
                    capitanvisitante.setText((String) document.get("CapitanVisitante"));
                    entrenadorlocal.setText((String) document.get("EntrenadorLocal"));
                    entrenadorvisitante.setText((String) document.get("EntrenadorVisitante"));
                    goleadorlocal.setText((String) document.get("GoleadorLocal"));
                    goleadorvisitante.setText((String) document.get("GoleadorVisitante"));
                    conflictivolocal.setText((String) document.get("JugadorConflictivoLocal"));
                    conflictivovisitante.setText((String) document.get("JugadorConflictivoVisitante"));

                } else {
                    Log.d("TAG", "No such document");
                }

            } else {
                Log.d("TAG", "get failed with ", task1.getException());
            }

        });

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(InfoInforme.this, Partidos.class);
                startActivity(intent);
            }
        });



        botoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoInforme.this);
                builder.setMessage("¿Está seguro de que desea eliminar el partido?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                db.collection("Informes").document(nombre)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(InfoInforme.this, "Informe eliminado correctamente", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(InfoInforme.this, "No se ha podido eliminar el informe. " + e, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                Intent intent = new Intent(InfoInforme.this, Partidos.class);
                                startActivity(intent);
                                Toast.makeText(InfoInforme.this, "Informe eliminado correctamente", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });
    }
}
