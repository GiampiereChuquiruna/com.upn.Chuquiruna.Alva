package com.example.comupnchuquirunaalvafinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainCartas extends AppCompatActivity {

    private TextView tvNombreD;
    private Button btnRCarta, btnVCarta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincartas);

        tvNombreD = findViewById(R.id.tvDuelistaName);
        btnRCarta = findViewById(R.id.btnRCarta);
        btnVCarta = findViewById(R.id.btnVCarta);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);
        tvNombreD.setText(nombre);

        btnRCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), CartaFomulario.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        btnVCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), ListaCarta.class);
                intent.putExtra("idDuelista", id);
                startActivity(intent);
            }
        });

    }
}
