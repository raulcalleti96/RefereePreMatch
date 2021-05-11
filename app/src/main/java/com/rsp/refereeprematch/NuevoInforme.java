package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NuevoInforme extends AppCompatActivity {

    Button botonguardar;
    Button botonVolver;
    FirebaseFirestore db;
    TextView competicion, fecha, recibo,goleadorlocal, goleadorvisitante,conflictivolocal, conflictivovisitante,jornada, capitanlocal, capitanvisitante,entrenadorlocal,entrenadorvisitante;
    Spinner local, visitante,estadio,arbitro,asistente1,asistente2;
    String partidoString;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevoinforme);
        db = FirebaseFirestore.getInstance();
        botonguardar = findViewById(R.id.guardainformenuevo);
        botonVolver = findViewById(R.id.volverbtninformei);
        local = findViewById(R.id.spinLocali);
        visitante = findViewById(R.id.spinvisitantei);
        competicion = findViewById(R.id.competiciontvin);
        fecha = findViewById(R.id.fechatvin);
        estadio = findViewById(R.id.spinestadioi);
        recibo = findViewById(R.id.recibotvin);
        arbitro = findViewById(R.id.spinarbitroi);
        asistente1 = findViewById(R.id.spinasis1i);
        asistente2 = findViewById(R.id.spinasis2);
        capitanlocal = findViewById(R.id.CapitanLin);
        capitanvisitante = findViewById(R.id.CapitanVin);
        entrenadorlocal = findViewById(R.id.entrenadorlocaln);
        entrenadorvisitante = findViewById(R.id.entrenadorvisitantein);
        goleadorlocal = findViewById(R.id.Goleadorlocalin);
        goleadorvisitante = findViewById(R.id.GoleadorVisitantein);
        conflictivolocal = findViewById(R.id.ConflictivoLin);
        conflictivovisitante = findViewById(R.id.ConflictivoVin);
        jornada = findViewById(R.id.jornadainformenuvo);
        puestaSpinner();

        botonguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partidoString = "informe"+arbitro.getSelectedItem().toString() + "jornada"+jornada;
                crearPartido();
                Intent intent = new Intent(NuevoInforme.this, MenuPrincipalArbitro.class);
                startActivity(intent);
            }
        });
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NuevoInforme.this, MenuPrincipalArbitro.class);
                startActivity(intent);
            }
        });

    }


    private void crearPartido(){

        Map<String, Object> user = new HashMap<>();
        user.put("ArbitroPrincipal", arbitro.getSelectedItem().toString());
        user.put("Asistente1", asistente1.getSelectedItem().toString());
        user.put("Asistente2", asistente2.getSelectedItem().toString());
        user.put("CapitanLocal", capitanlocal.getText().toString());
        user.put("CapitanVisitante", capitanvisitante.getText().toString());
        user.put("Competicion", competicion.getText().toString());
        user.put("EntrenadorLocal", entrenadorlocal.getText().toString());
        user.put("EntrenadorVisitante", entrenadorvisitante.getText().toString());
        user.put("EquipoLocal", local.getSelectedItem().toString());
        user.put("EquipoVisitante", visitante.getSelectedItem().toString());
        user.put("Estadio", estadio.getSelectedItem().toString());
        user.put("Fecha", fecha.getText().toString());
        user.put("GoleadorLocal", goleadorlocal.getText().toString());
        user.put("GoleadorVisitante", goleadorvisitante.getText().toString());
        user.put("Jornada", jornada.getText().toString());
        user.put("JugadorConflictivoLocal", conflictivolocal.getText().toString());
        user.put("JugadorConflictivoVisitante", conflictivovisitante.getText().toString());
        user.put("Nombre", partidoString);
        user.put("Recibo", recibo.getText().toString());

        db.collection("Informes").document(partidoString)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Añadido correctamente");
                        Intent intent = new Intent(NuevoInforme.this, MenuPrincipalArbitro.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error al añadirlo", e);
                        Intent intent = new Intent(NuevoInforme.this, MenuPrincipalArbitro.class);
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
