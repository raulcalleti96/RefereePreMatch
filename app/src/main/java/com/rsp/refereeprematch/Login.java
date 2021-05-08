package com.rsp.refereeprematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private static final String TAG = "emaipass";
    private FirebaseAuth mAuth;
    TextView email;
    TextView password;
    FirebaseFirestore db;
    boolean arbitro = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        mAuth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.editTextTextEmailAddress);
        password = (TextView) findViewById(R.id.editTextTextPassword);
        db = FirebaseFirestore.getInstance();

    }

    public void iniciarSesion(View view) {

        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            db.collection("users").document(email.getText().toString()).get().addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {

                                    DocumentSnapshot document = task1.getResult();

                                    if (document.exists()) {

                                        arbitro = (boolean) document.get("arbitro");

                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Intent intent;
                                        if(arbitro){
                                            Constantes.IDUSUARIO = 0;
                                            intent = new Intent(Login.this, MenuPrincipalArbitro.class);

                                        }else{
                                            Constantes.IDUSUARIO = 1;
                                            intent = new Intent(Login.this, MenuPrincipalDelegado.class);

                                        }

                                        startActivity(intent);
                                        updateUI(user);

                                    } else {

                                        Log.d(TAG, "No such document");
                                    }

                                } else {
                                    Log.d(TAG, "get failed with ", task1.getException());
                                }

                            });

                        } else {

                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {

    }
        @Override
        public void onStart(){
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
        }


}
