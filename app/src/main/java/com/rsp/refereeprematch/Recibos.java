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

public class Recibos extends AppCompatActivity {


    //Firebase
    public FirebaseFirestore db;

    //Android
    public ListView listView;
    private ArrayList<Recibo> arrayList = new ArrayList<>();
    Button volverrecibo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibos);
        db = FirebaseFirestore.getInstance();
        listView =  (ListView) findViewById(R.id.listadoView);
        volverrecibo = findViewById(R.id.btnvolverlistadorecibo);
        loadDatainListview();

        volverrecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Constantes.IDUSUARIO  == 0) {
                    Intent intent = new Intent(Recibos.this, MenuPrincipalArbitro.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Recibos.this, MenuPrincipalDelegado.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void loadDatainListview(){

        db.collection("Recibos").get()
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
                                Recibo recibo = d.toObject(Recibo.class);

                                // after getting data from Firebase we are
                                // storing that data in our array list
                                arrayList.add(recibo);
                            }
                            // after that we are passing our array list to our adapter class.
                            RecibosAdapter recibosAdapter = new RecibosAdapter(Recibos.this, arrayList);

                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.
                            listView.setAdapter(recibosAdapter);
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(Recibos.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // we are displaying a toast message
                // when we get any error from Firebase.
                Toast.makeText(Recibos.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
