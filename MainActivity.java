package com.example.dogforecess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp;
    TextView tvSignIn;
    EditText emailId, password;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        btnSignUp=findViewById(R.id.button);
        tvSignIn=findViewById(R.id.textView);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
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
                   Toast.makeText(MainActivity.this,"Fields are Empty",Toast.LENGTH_SHORT);

                   }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign Up UnSuccessful,please try again",Toast.LENGTH_SHORT);
                            }
                          else{
                               startActivity(new Intent(MainActivity.this,GenerateActivity.class));
                            }
                        }

                    });

               }
          else {
                   Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_SHORT);
               }

            }
        });
       tvSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,GenerateActivity.class);
               startActivity(i);
           }
       });
    }
}