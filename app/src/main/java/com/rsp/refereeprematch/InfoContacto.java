package com.rsp.refereeprematch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class InfoContacto extends AppCompatActivity {

    Button botonEliminar;
    Button botonVolver;
    FirebaseFirestore db;
    String email;
    TextView nombre, apellidos, telefono, fechanacimiento, dni, categoria, clave;
    CheckBox delegado, arbi;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infocontacto);
        botonEliminar = findViewById(R.id.eliminarcontacto);
        botonVolver = findViewById(R.id.volverbtn);
        db = FirebaseFirestore.getInstance();
        nombre = findViewById(R.id.nombrept);
        apellidos = findViewById(R.id.apellidospt);
        telefono = findViewById(R.id.telefononuevotv);
        fechanacimiento = findViewById(R.id.nacimientopt);
        dni = findViewById(R.id.dnipt);
        categoria = findViewById(R.id.categoriapt);
        clave = findViewById(R.id.clavenuevotv);
        delegado = findViewById(R.id.delegadocb);
        arbi = findViewById(R.id.arbitrocb);

        if(Constantes.IDUSUARIO == 0) {
            botonEliminar.setVisibility(View.GONE);
            rellenaDatosArbi();
        }else{
            Bundle b = getIntent().getExtras();
            email = b.getString("email");
            botonEliminar.setVisibility(View.VISIBLE);
            rellenaDatosDelegado();
        }

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Constantes.IDUSUARIO == 0) {
                    Intent intent = new Intent(InfoContacto.this, MenuPrincipalArbitro.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(InfoContacto.this, Arbitros.class);
                    startActivity(intent);
                }

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
                                                Toast.makeText(InfoContacto.this, "No se ha podido eliminar el usuario." + e, Toast.LENGTH_SHORT).show();
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


    public void rellenaDatosArbi(){

        db.collection("users").document(Constantes.EMAILUSER).get().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {

                DocumentSnapshot document = task1.getResult();

                if (document.exists()) {

                    nombre.setText((String) document.get("Nombre"));
                    apellidos.setText((String) document.get("Apellidos"));
                    telefono.setText((String)document.get("Telefono"));
                    fechanacimiento.setText((String) document.get("FechaNacimiento"));
                    dni.setText((String) document.get("DNI"));
                    categoria.setText((String) document.get("Categoria"));


                    if((boolean) document.get("delegado")){
                        delegado.setChecked(true);
                    }else{
                        arbi.setChecked(true);
                    }

                } else {

                    Log.d("TAG", "No such document");
                }

            } else {
                Log.d("TAG", "get failed with ", task1.getException());
            }

        });
    }



    public void rellenaDatosDelegado(){

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


                    if((boolean) document.get("delegado")){
                        delegado.setChecked(true);
                    }else if((boolean) document.get("arbitro")){
                        arbi.setChecked(true);
                    }

                } else {

                    Log.d("TAG", "No such document");
                }

            } else {
                Log.d("TAG", "get failed with ", task1.getException());
            }

        });

    }

}