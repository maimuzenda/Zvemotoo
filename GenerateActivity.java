package com.example.dogforecess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GenerateActivity extends AppCompatActivity {


    Button btnGENERATE;
    Button btnUPDATE;
    Button btnVIEW;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        btnGENERATE=findViewById(R.id.btnGENERATE);
        btnUPDATE=findViewById(R.id.btnUPDATE);
        btnVIEW=findViewById(R.id.btnVIEW);
        txtView=findViewById(R.id.txtView);


        btnUPDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intoUDT = new Intent(GenerateActivity.this, DatabaseActivity.class);
                startActivity(intoUDT);
            }
        });
        btnVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intoVIEW = new Intent(GenerateActivity.this, UserActivity.class);
                startActivity(intoVIEW);


            }
        });

        btnGENERATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intoGNRT = new Intent(GenerateActivity.this, UpdateActivity.class);
                startActivity(intoGNRT);


            }
        });
    }

}
