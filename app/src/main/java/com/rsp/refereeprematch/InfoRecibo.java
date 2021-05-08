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


public class InfoRecibo extends AppCompatActivity {

    TextView pagoTotal, pagoRFEF, pagoA1, pagoA2, pagoArbitro,partido;
    Button botoneliminar,botonVolver;
    String nombre;
    int id;
    FirebaseFirestore db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inforecibos);
        Bundle b = getIntent().getExtras();
        nombre = b.getString("nombre");
        id = b.getInt("id");
        db = FirebaseFirestore.getInstance();
        pagoTotal = findViewById(R.id.pagoTotal);
        pagoRFEF = findViewById(R.id.pagoRFEF);
        pagoA1 = findViewById(R.id.pagoA1);
        pagoA2 = findViewById(R.id.pagoA2);
        pagoArbitro = findViewById(R.id.pagoArbitro);
        partido = findViewById(R.id.partidotv);
        botoneliminar = findViewById(R.id.eliminarbtnRecibos);
        botonVolver = findViewById(R.id.volverbtnRecibo);
        rellenaDatosEstadio();

        Log.d("TAG",String.valueOf(id));
        if(id == 0) {
            botoneliminar.setVisibility(View.GONE);
        }else{
            botoneliminar.setVisibility(View.VISIBLE);
        }


    }

    public void rellenaDatosEstadio() {

        db.collection("Recibos").document(nombre).get().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {

                DocumentSnapshot document = task1.getResult();

                if (document.exists()) {

                    pagoTotal.setText((String) document.get("PagoTotal"));
                    pagoRFEF .setText((String) document.get("PagoRFEF"));
                    pagoA1.setText((String) document.get("PagoAsistente1"));
                    pagoA2.setText((String) document.get("PagoAsistente2"));
                    pagoArbitro.setText((String) document.get("PagoArbitroPrincipal"));
                    partido.setText((String) document.get("Partido"));




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

                Intent intent = new Intent(InfoRecibo.this, Recibos.class);
                startActivity(intent);
            }
        });



        botoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoRecibo.this);
                builder.setMessage("¿Está seguro de que desea eliminar el recibo?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                db.collection("Recibos").document(nombre)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(InfoRecibo.this, "Recibo eliminado correctamente", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(InfoRecibo.this, "No se ha podido eliminar el recibo. " + e, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                Intent intent = new Intent(InfoRecibo.this, Recibos.class);
                                startActivity(intent);
                                Toast.makeText(InfoRecibo.this, "Recibo eliminado correctamente", Toast.LENGTH_SHORT).show();
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
