package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Partidos extends AppCompatActivity {


    //Firebase
    public FirebaseFirestore db;
    int id;
    //Android
    public ListView listView;
    private ArrayList<Partido> arrayList = new ArrayList<>();
    Button volver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partidos);
        Bundle b = getIntent().getExtras();
        db = FirebaseFirestore.getInstance();
        listView =  (ListView) findViewById(R.id.listadoView);
        volver = findViewById(R.id.btnvolverlistadopartidos);
        loadDatainListview();

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Constantes.IDUSUARIO  == 0) {
                    Intent intent = new Intent(Partidos.this, MenuPrincipalArbitro.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Partidos.this, MenuPrincipalDelegado.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void loadDatainListview(){

        db.collection("Partidos").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding
                            // our progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                Partido partido = d.toObject(Partido.class);


                                    // after getting data from Firebase we are
                                    // storing that data in our array list
                                    arrayList.add(partido);

                            }
                            // after that we are passing our array list to our adapter class.
                            PartidosAdapter partidosAdapter = new PartidosAdapter(Partidos.this, arrayList,id);


                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.
                            listView.setAdapter(partidosAdapter);
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(Partidos.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // we are displaying a toast message
                // when we get any error from Firebase.
                Toast.makeText(Partidos.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
