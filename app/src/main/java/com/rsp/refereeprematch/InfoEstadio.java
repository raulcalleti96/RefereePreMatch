package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class InfoEstadio extends AppCompatActivity {

    TextView nombre, aforo, superficie, direccion, ciudad, localidad;
    ImageView  imagenEstadio;
    Button botonVolver;
    String id;
    FirebaseFirestore db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoestadios);
        Bundle b = getIntent().getExtras();
        id = b.getString("id");
        db = FirebaseFirestore.getInstance();
        botonVolver = findViewById(R.id.volverbtnestadio);
        nombre = findViewById(R.id.partidotv);
        aforo = findViewById(R.id.aforoinfoestadio);
        superficie = findViewById(R.id.superficieinfo);
        direccion = findViewById(R.id.direccioninfotv);
        ciudad = findViewById(R.id.ciudadinfoestadiotv);
        localidad = findViewById(R.id.localidadinfoestadio);
        imagenEstadio = findViewById(R.id.imagenestadio);
        rellenaDatosEstadio();
    }

    public void rellenaDatosEstadio() {

        db.collection("Estadios").document(id).get().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {

                DocumentSnapshot document = task1.getResult();

                if (document.exists()) {

                    nombre.setText((String) document.get("Nombre"));
                    aforo.setText((String) document.get("Aforo"));
                    superficie.setText((String) document.get("Superficie"));
                    direccion.setText((String) document.get("Direccion"));
                    ciudad.setText((String) document.get("Ciudad"));
                    localidad.setText((String) document.get("Localidad"));
                    Picasso.get().load((String) document.get("Foto")).into(imagenEstadio);

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

                Intent intent = new Intent(InfoEstadio.this, Estadios.class);
                startActivity(intent);
            }
        });
    }
}
