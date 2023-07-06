package com.example.comupnchuquirunaalvafinal;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetalleCarta extends AppCompatActivity {

    TextView tvNombreC, tvAtaque, tvDefensa, tvLong, tvLat;
    ImageView imgCarta;
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
        tvNombreC.setText(nombreC);

        tvAtaque = findViewById(R.id.tvAC);
        tvAtaque.setText(String.valueOf(puntosAtaque));

        tvDefensa = findViewById(R.id.tvDC);
        tvDefensa.setText(String.valueOf(puntosDefensa));

        tvLong = findViewById(R.id.tvLonguitud);
        tvLong.setText(String.valueOf(longitud));

        tvLat = findViewById(R.id.tvLatitude);
        tvLat.setText(String.valueOf(latitud));

        imgCarta = findViewById(R.id.imageView);
        String urlImagen = getIntent().getStringExtra("url");

        Picasso.get()
                .load(url)
                .into(imgCarta);


    }
}
