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


public class InfoPartido extends AppCompatActivity {

    TextView local, visitante, competicion, fecha, estadio, recibo,arbitro,asistente1,asistente2;
    Button botoneliminar,botonVolver;
    String nombre;
    int id;
    FirebaseFirestore db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopartidos);
        Bundle b = getIntent().getExtras();
        nombre = b.getString("nombre");
        id = b.getInt("id");
        db = FirebaseFirestore.getInstance();
        botonVolver = findViewById(R.id.volverbtnpartido);
        botoneliminar = findViewById(R.id.guardarnuevocontacto);
        local = findViewById(R.id.apellidosnuevotv);
        visitante = findViewById(R.id.equipovisitantetv);
        competicion = findViewById(R.id.competiciontv);
        fecha = findViewById(R.id.fechatv);
        estadio = findViewById(R.id.estadiotv);
        recibo = findViewById(R.id.recibotv);
        arbitro = findViewById(R.id.arbitrotv);
        asistente1 = findViewById(R.id.asistente1tv);
        asistente2 = findViewById(R.id.asistente2tv);
        rellenaDatosEstadio();

        if(id == 0) {
            botoneliminar.setVisibility(View.GONE);
        }else{
            botoneliminar.setVisibility(View.VISIBLE);
        }
    }

    public void rellenaDatosEstadio() {

        db.collection("Partidos").document(nombre).get().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {

                DocumentSnapshot document = task1.getResult();

                if (document.exists()) {

                    local.setText((String) document.get("EquipoLocal"));
                    visitante.setText((String) document.get("EquipoLocal"));
                    competicion.setText((String) document.get("Competicion"));
                    fecha.setText((String) document.get("Fecha"));
                    estadio.setText((String) document.get("Estadio"));
                    recibo.setText((String) document.get("Recibo"));
                    arbitro.setText((String) document.get("Arbitro"));
                    asistente1.setText((String) document.get("Asistente1"));
                    asistente2.setText((String) document.get("Asistente2"));

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

                Intent intent = new Intent(InfoPartido.this, Partidos.class);
                startActivity(intent);
            }
        });



        botoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoPartido.this);
                builder.setMessage("¿Está seguro de que desea eliminar el partido?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                db.collection("Partidos").document(nombre)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(InfoPartido.this, "Partido eliminado correctamente", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(InfoPartido.this, "No se ha podido eliminar el partido. " + e, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                Intent intent = new Intent(InfoPartido.this, Partidos.class);
                                startActivity(intent);
                                Toast.makeText(InfoPartido.this, "Partido eliminado correctamente", Toast.LENGTH_SHORT).show();
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
