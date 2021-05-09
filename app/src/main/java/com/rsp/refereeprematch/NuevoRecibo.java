package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NuevoRecibo extends AppCompatActivity {

    Button botonguardar;
    Button botonVolver;
    FirebaseFirestore db;
    TextView pagoArbitro, pagoAsistente1, pagoAsistente2, pagoRFEF,pagoTotal;
    Spinner partido;
    String partidoString, local, visitante;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevorecibo);
        db = FirebaseFirestore.getInstance();
        botonguardar = findViewById(R.id.guardarnuevorecibo);
        botonVolver = findViewById(R.id.volvernuevorecibo);
        pagoArbitro = findViewById(R.id.pagoArbitro);
        partido = findViewById(R.id.spinnerpartidorecibo);
        pagoAsistente1 = findViewById(R.id.pagoA1);
        pagoAsistente2 = findViewById(R.id.pagoA2);
        pagoRFEF = findViewById(R.id.pagoRFEF);
        pagoTotal = findViewById(R.id.pagoTotal);
        puestaSpinner();

        botonguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partidoString = "Recibo"+partido.getSelectedItem().toString();
                crearPartido();
                Intent intent = new Intent(NuevoRecibo.this, MenuPrincipalDelegado.class);
                startActivity(intent);

            }
        });
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NuevoRecibo.this, MenuPrincipalDelegado.class);
                startActivity(intent);
            }
        });

        partido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                db.collection("Partidos").document(partido.getSelectedItem().toString()).get().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        DocumentSnapshot document = task1.getResult();
                        if (document.exists()) {
                            pagoTotal.setText((String) document.get("ReciboTotal"));
                        } else {
                            Log.d("TAG", "No such document");
                        }
                    } else {
                        Log.d("TAG", "get failed with ", task1.getException());
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

    }


    private void crearPartido(){



        Map<String, Object> user = new HashMap<>();
        user.put("Nombre", partidoString);
        user.put("PagoArbitroPrincipal", pagoArbitro.getText().toString());
        user.put("PagoAsistente1", pagoAsistente1.getText().toString());
        user.put("PagoAsistente2", pagoAsistente2.getText().toString());
        user.put("PagoRFEF", pagoRFEF.getText().toString());
        user.put("PagoTotal", pagoTotal.getText().toString());
        user.put("Partido", partido.getSelectedItem().toString());


        db.collection("Recibos").document(partidoString)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Añadido correctamente");
                        Intent intent = new Intent(NuevoRecibo.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error al añadirlo", e);
                        Intent intent = new Intent(NuevoRecibo.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                });

    }





    private void puestaSpinner(){

        //Spinner partido
        CollectionReference partidos = db.collection("Partidos");
        List<String> locale = new ArrayList<>();
        ArrayAdapter<String> adapterl = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, locale);
        adapterl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partido.setAdapter(adapterl);
        partidos.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("Nombre");
                        locale.add(subject);
                    }
                    adapterl.notifyDataSetChanged();
                }
            }
        });

    }
}
