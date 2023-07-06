package com.example.comupnchuquirunaalvafinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetalleCarta extends AppCompatActivity {

    TextView tvNombreC, tvAtaque, tvDefensa, tvLong, tvLat;
    ImageView imgCarta;
    Button btnMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        String nombreC = getIntent().getStringExtra("nombre");
        int puntosAtaque = getIntent().getIntExtra("ataque", 0);
        int puntosDefensa = getIntent().getIntExtra("defensa", 0);
        int longitud = getIntent().getIntExtra("longitud", 0);
        int latitud = getIntent().getIntExtra("latitud", 0);
        String url = getIntent().getStringExtra("url");

        tvNombreC = findViewById(R.id.tvNC);
        tvAtaque = findViewById(R.id.tvAC);
        tvDefensa = findViewById(R.id.tvDC);
        tvLong = findViewById(R.id.tvLonguitud);
        tvLat = findViewById(R.id.tvLatitude);

        tvNombreC.setText(nombreC);
        tvAtaque.setText(String.valueOf(puntosAtaque));
        tvDefensa.setText(String.valueOf(puntosDefensa));
        tvLong.setText(String.valueOf(longitud));
        tvLat.setText(String.valueOf(latitud));

        imgCarta = findViewById(R.id.imageView);
        String urlImagen = getIntent().getStringExtra("url");

        Picasso.get()
                .load(url)
                .into(imgCarta);

        btnMapa = findViewById(R.id.btnVerMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("latitud", latitud);
                intent.putExtra("longitud", longitud);
                startActivity(intent);
            }
        });
    }
}
