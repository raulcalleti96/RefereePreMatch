package com.rsp.refereeprematch;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Estadios extends AppCompatActivity {


    //Firebase
    public FirebaseFirestore db;

    //Android
    public ListView listView;
    private ArrayList<Estadio> arrayList = new ArrayList<Estadio>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadios);
        db = FirebaseFirestore.getInstance();
        listView =  (ListView) findViewById(R.id.listadoView);
        loadDatainListview();
    }

    private void loadDatainListview(){

        db.collection("Estadios").get()
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
                                Estadio estadio = d.toObject(Estadio.class);

                                // after getting data from Firebase we are
                                // storing that data in our array list
                                arrayList.add(estadio);
                            }
                            // after that we are passing our array list to our adapter class.
                            EstadiosAdapter estadiosAdapter = new EstadiosAdapter(Estadios.this, arrayList);

                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.
                            listView.setAdapter(estadiosAdapter);
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(Estadios.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // we are displaying a toast message
                // when we get any error from Firebase.
                Toast.makeText(Estadios.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

