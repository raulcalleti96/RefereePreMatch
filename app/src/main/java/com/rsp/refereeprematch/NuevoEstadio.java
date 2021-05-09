package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class NuevoEstadio extends AppCompatActivity {

    Button botonguardar;
    Button botonVolver;
    FirebaseFirestore db;
    TextView nombre, aforo, superficie, direccion, ciudad, localidad, url;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevoestadio);
        db = FirebaseFirestore.getInstance();
        botonguardar = findViewById(R.id.guardarnuevoestadio);
        botonVolver = findViewById(R.id.botonvolvernuevoestadio);
        nombre = findViewById(R.id.nombrenuevoestadiotv);
        aforo = findViewById(R.id.AforoNuevoEstadiotv);
        superficie = findViewById(R.id.superficienuevoTv);
        direccion = findViewById(R.id.direccionnuevotv);
        ciudad = findViewById(R.id.ciudadnuevoestadiotv);
        localidad = findViewById(R.id.localidadnuevoestadiotv);
        url = findViewById(R.id.urlnuevotv);


        botonguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearEstadio();
                Intent intent = new Intent(NuevoEstadio.this, MenuPrincipalDelegado.class);
                startActivity(intent);
            }
        });
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NuevoEstadio.this, MenuPrincipalDelegado.class);
                startActivity(intent);
            }
        });
    }

    private void crearEstadio(){

        Map<String, Object> user = new HashMap<>();
        user.put("Nombre", nombre.getText().toString());
        user.put("Aforo", aforo.getText().toString());
        user.put("Superficie", superficie.getText().toString());
        user.put("Direccion", direccion.getText().toString());
        user.put("Ciudad", ciudad.getText().toString());
        user.put("Localidad", localidad.getText().toString());
        user.put("url", url.getText().toString());

        db.collection("Estadios").document(nombre.getText().toString())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Añadido correctamente");
                        Intent intent = new Intent(NuevoEstadio.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error al añadirlo", e);
                        Intent intent = new Intent(NuevoEstadio.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                });

    }
}
