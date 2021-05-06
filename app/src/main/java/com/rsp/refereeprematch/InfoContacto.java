package com.rsp.refereeprematch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class InfoContacto extends AppCompatActivity {

    Button botonEliminar;
    Button botonVolver;
    FirebaseFirestore db;
    String email;
    int id;
    TextView nombre, apellidos, telefono, fechanacimiento, dni, categoria, clave;
    ImageView volver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infocontacto);
        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        email = b.getString("email");
        botonEliminar = findViewById(R.id.eliminarbtn);
        botonVolver = findViewById(R.id.volverbtn);
        db = FirebaseFirestore.getInstance();
        nombre = findViewById(R.id.nombrept);
        apellidos = findViewById(R.id.apellidospt);
        telefono = findViewById(R.id.telefonophone);
        fechanacimiento = findViewById(R.id.nacimientopt);
        dni = findViewById(R.id.dnipt);
        categoria = findViewById(R.id.categoriapt);
        clave = findViewById(R.id.clavepass);

        /*
        if(id == 0) {
            botonEliminar.setVisibility(View.GONE);
        }else{
            botonEliminar.setVisibility(View.VISIBLE);
        }
        */


        rellenaDatos();
    }

    public void rellenaDatos(){

        db.collection("users").document(email).get().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {

                DocumentSnapshot document = task1.getResult();

                if (document.exists()) {

                    nombre.setText((String) document.get("Nombre"));
                    apellidos.setText((String) document.get("Apellidos"));
                    telefono.setText((String)document.get("Telefono"));
                    fechanacimiento.setText((String) document.get("FechaNacimiento"));
                    dni.setText((String) document.get("DNI"));
                    categoria.setText((String) document.get("Categoria"));

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

                    Intent intent = new Intent(InfoContacto.this, Arbitros.class);
                    startActivity(intent);
            }
        });



        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoContacto.this);
                builder.setMessage("¿Está seguro de que desea eliminar el usuario?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                db.collection("users").document(email)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(InfoContacto.this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(InfoContacto.this, "No se ha podido eliminar el usuario. " + e, Toast.LENGTH_SHORT).show();
                                            }
                                        });



                                Intent intent = new Intent(InfoContacto.this, Arbitros.class);
                                startActivity(intent);
                                Toast.makeText(InfoContacto.this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();
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