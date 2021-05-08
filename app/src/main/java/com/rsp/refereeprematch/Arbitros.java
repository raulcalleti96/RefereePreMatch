package com.rsp.refereeprematch;

import android.os.Bundle;
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

public class Arbitros extends AppCompatActivity {


    //Firebase
    public FirebaseFirestore db;

    //Android
    public ListView listView;
    private ArrayList<Arbitro> arrayList = new ArrayList<Arbitro>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arbitros);
        db = FirebaseFirestore.getInstance();
        listView =  (ListView) findViewById(R.id.listadoView);
        loadDatainListview();
    }

    private void loadDatainListview(){

        db.collection("users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {

                                Arbitro users = d.toObject(Arbitro.class);

                                arrayList.add(users);
                            }

                            ArbitrosAdapter arbitrosAdapter = new ArbitrosAdapter(Arbitros.this, arrayList);

                            listView.setAdapter(arbitrosAdapter);
                        } else {

                            Toast.makeText(Arbitros.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Arbitros.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

