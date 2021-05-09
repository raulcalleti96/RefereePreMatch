package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class NuevoContacto extends AppCompatActivity {

    Button botonguardar;
    Button botonVolver;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    TextView nombre, apellidos, telefono, fechanacimiento, dni, categoria, clave1,email;
    CheckBox delegado, arbi;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevocontacto);
        botonguardar = findViewById(R.id.guardarnuevocontacto);
        botonVolver = findViewById(R.id.volvernuevocontactobtn);
        db = FirebaseFirestore.getInstance();
        nombre = findViewById(R.id.nombrenuevotv);
        apellidos = findViewById(R.id.apellidosnuevotv);
        telefono = findViewById(R.id.telefononuevotv);
        fechanacimiento = findViewById(R.id.nacimientonuevotv);
        dni = findViewById(R.id.dninuevotv);
        categoria = findViewById(R.id.categorianuevotv);
        clave1 = findViewById(R.id.clavenuevotv);
        delegado = findViewById(R.id.delegadonuevocb);
        arbi = findViewById(R.id.arbitronuevocb);
        email = findViewById(R.id.emailnuevocontactotv);
        mAuth = FirebaseAuth.getInstance();


        botonguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearCuenta();
                Intent intent = new Intent(NuevoContacto.this, MenuPrincipalDelegado.class);
                startActivity(intent);
            }
        });
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NuevoContacto.this, MenuPrincipalDelegado.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    private void crearCuenta(){

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), clave1.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());

                        }
                    }
                });
        Map<String, Object> user = new HashMap<>();
        user.put("Name", nombre.getText().toString());
        user.put("Apellidos", apellidos.getText().toString());
        user.put("Categoria", categoria.getText().toString());
        user.put("DNI", dni.getText().toString());
        user.put("FechaNacimiento", fechanacimiento.getText().toString());
        user.put("Telefono", telefono.getText().toString());
        user.put("arbitro", arbi.isChecked());
        user.put("delegado", delegado.isChecked());
        user.put("email",email.getText().toString());

        db.collection("users").document(email.getText().toString())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Añadido correctamente");
                        Intent intent = new Intent(NuevoContacto.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error al añadirlo", e);
                        Intent intent = new Intent(NuevoContacto.this, MenuPrincipalDelegado.class);
                        startActivity(intent);
                    }
                });

    }



}

