package com.example.foodrecipeproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sigin extends AppCompatActivity {

    EditText lemail,lpassword;
    FirebaseAuth mAuth;
    Button signup,signin;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent  intent =new Intent(getApplicationContext(),Home.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        mAuth=FirebaseAuth.getInstance();
        signup =findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        lemail =findViewById(R.id.email);
        lpassword =findViewById(R.id.loginpassword);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Sigin.this,Signup.class);
                    startActivity(intent);}

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = lemail.getText().toString();
                password = lpassword.getText().toString();


//                Intent intent = new Intent(Sigin.this, Home.class);
//                startActivity(intent);
//                finish();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Sigin.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Sigin.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Sigin.this, "Login Successsful", Toast.LENGTH_SHORT).show();
                                    Intent  intent =new Intent(getApplicationContext(),Home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Sigin.this, "Please enter correct password/email Or Please SignUp",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });//copied from firebase;
            }
            });

        }
    }

