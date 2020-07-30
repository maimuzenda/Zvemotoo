package com.example.dogforecess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;

import java.util.List;

public class loginActivity extends AppCompatActivity {

    Button btnSignIn;
    TextView tvSignUp;
    EditText emailId, password;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        btnSignIn = findViewById(R.id.button);
        tvSignUp = findViewById(R.id.textView);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {



            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if(mAuth!=null){
                    Toast.makeText(loginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(loginActivity.this,HomeActivity.class);
                    startActivity(i);
                }
         else{
                    Toast.makeText(loginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }

            }

        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= emailId.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty()) {
                    emailId.setError("Please enter Email");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();

                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(loginActivity.this,"Fields are Empty",Toast.LENGTH_SHORT);

                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(loginActivity.this,"logIn error please log in again",Toast.LENGTH_SHORT);
                            }
                        else{
                                Intent inToHome=new Intent(loginActivity.this,HomeActivity.class);
                                startActivity(inToHome);
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(loginActivity.this,"Error Occured",Toast.LENGTH_SHORT);
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSignUp=new Intent(loginActivity.this,MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }
    public void onStart(){
        mAuth.addAuthStateListener(mAuthStateListener);
    }




}


