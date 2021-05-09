package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NuevoPartido extends AppCompatActivity {


    Button botonguardar;
    Button botonVolver;
    FirebaseFirestore db;
    TextView partido, competicion, fecha, recibo;
    Spinner local, visitante,estadio,arbitro,asistente1,asistente2;
    String partidoString;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevopartido);
        db = FirebaseFirestore.getInstance();
        botonguardar = findViewById(R.id.guardarnuevopartido);
        botonVolver = findViewById(R.id.botonvolvernuevoestadio);
        partido = findViewById(R.id.partidotv);
        local = findViewById(R.id.spinnerLocal);
        visitante = findViewById(R.id.spinnerVisitante);
        competicion = findViewById(R.id.competiciontv);
        fecha = findViewById(R.id.fechatv);
        estadio = findViewById(R.id.spinnerEstadio);
        recibo = findViewById(R.id.recibotv);
        arbitro = findViewById(R.id.spinnerA);
        asistente1 = findViewById(R.id.spinnerA1);
        asistente2 = findViewById(R.id.spinnerA2);
        puestaSpinner();

        botonguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partidoString = arbitro.getSelectedItem().toString() + fecha.getText().toString();
                crearPartido();
                Intent intent = new Intent(NuevoPartido.this, MenuPrincipalDelegado.class);
                startActivity(intent);
            }
        });
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NuevoPartido.this, MenuPrincipalDelegado.class);
                startActivity(intent);
            }
        });

    }


    private void crearPartido(){

        Map<String, Object> user = new HashMap<>();
        user.put("Arbitro", arbitro.getSelectedItem().toString());
        user.put("Asistente1", asistente1.getSelectedItem().toString());
        user.put("Asistente2", asistente2.getSelectedItem().toString());
        user.put("Competicion", competicion.getText().toString());
        user.put("EquipoLocal", local.getSelectedItem().toString());
        user.put("EquipoVisitante", visitante.getSelectedItem().toString());
        user.put("Estadio", estadio.getSelectedItem().toString());
        user.put("Fecha", fecha.getText().toString());
        user.put("Nombre", partidoString);
        user.put("Recibo", recibo.getText().toString());

        db.collection("Partidos").document(partidoString)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Añadido correctamente");
                        Intent intent = new Intent(NuevoPartido.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error al añadirlo", e);
                        Intent intent = new Intent(NuevoPartido.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                });

    }

    private void puestaSpinner(){

        //Spinner Equipo Local
        CollectionReference equipolocal = db.collection("Clubes");
        List<String> locale = new ArrayList<>();
        ArrayAdapter<String> adapterl = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, locale);
        adapterl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        local.setAdapter(adapterl);
        equipolocal.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Spinner Equipo visitante
        CollectionReference equipovisitante = db.collection("Clubes");
        List<String> visitantee = new ArrayList<>();
        ArrayAdapter<String> adapterv = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, visitantee);
        adapterv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        visitante.setAdapter(adapterv);
        equipovisitante.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("Nombre");
                        visitantee.add(subject);
                    }
                    adapterv.notifyDataSetChanged();
                }
            }
        });

        //Spinner Estadio
        CollectionReference eleccionestadio = db.collection("Estadios");
        List<String> estadioo = new ArrayList<>();
        ArrayAdapter<String> adaptere = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, estadioo);
        adaptere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadio.setAdapter(adaptere);
        eleccionestadio.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("Nombre");
                        estadioo.add(subject);
                    }
                    adaptere.notifyDataSetChanged();
                }
            }
        });

        //Spinner arbitro
        CollectionReference eleccionarbi = db.collection("users");
        List<String> userss = new ArrayList<>();
        ArrayAdapter<String> adaptera = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, userss);
        adaptere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arbitro.setAdapter(adaptera);
        eleccionarbi.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("Nombre");
                        userss.add(subject);
                    }
                    adaptera.notifyDataSetChanged();
                }
            }
        });

        //Spinner asistente1
        CollectionReference elecciona1 = db.collection("users");
        List<String> users1 = new ArrayList<>();
        ArrayAdapter<String> adaptera1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, users1);
        adaptera1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asistente1.setAdapter(adaptera1);
        elecciona1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("Nombre");
                        users1.add(subject);
                    }
                    adaptera1.notifyDataSetChanged();
                }
            }
        });

        //Spinner asistente2
        CollectionReference elecciona2 = db.collection("users");
        List<String> users2 = new ArrayList<>();
        ArrayAdapter<String> adaptera2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, users2);
        adaptera2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asistente2.setAdapter(adaptera2);
        elecciona2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("Nombre");
                        users2.add(subject);
                    }
                    adaptera2.notifyDataSetChanged();
                }
            }
        });
    }
}
