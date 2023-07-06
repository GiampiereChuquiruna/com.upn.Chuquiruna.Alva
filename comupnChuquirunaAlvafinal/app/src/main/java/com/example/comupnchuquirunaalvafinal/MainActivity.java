package com.example.comupnchuquirunaalvafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRD = this.findViewById(R.id.btnRegistrarD);
        Button btnVD = this.findViewById(R.id.btnVerD);

        btnRD.setOnClickListener(view -> {
            Intent intent =  new Intent(getApplicationContext(), DuelistaFormulario.class);
            startActivity(intent);
        });

        btnVD.setOnClickListener(view -> {
            Intent intent =  new Intent(getApplicationContext(), ListaDuelista.class);
            startActivity(intent);
        });
    }
}