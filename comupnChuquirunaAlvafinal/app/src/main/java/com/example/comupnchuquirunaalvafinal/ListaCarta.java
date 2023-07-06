package com.example.comupnchuquirunaalvafinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupnchuquirunaalvafinal.adapters.CartaAdapter;
import com.example.comupnchuquirunaalvafinal.entities.Carta;
import com.example.comupnchuquirunaalvafinal.services.CartaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaCarta extends AppCompatActivity {
    public RecyclerView rvListaM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacartas);

        Intent intent = getIntent();
        int idDuelista = intent.getIntExtra("idDuelista", 0);  // Recibir el idCuenta

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64a6b3f2096b3f0fcc805e89.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartaService service = retrofit.create(CartaService.class);
        Call<List<Carta>> call = service.getAll(idDuelista);
        call.enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(Call<List<Carta>> call, Response<List<Carta>> response) {
                if (response.isSuccessful()) {
                    List<Carta> cartas = response.body();
                    rvListaM = findViewById(R.id.rvListaCartas);
                    rvListaM.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvListaM.setAdapter(new CartaAdapter(cartas));
                } else {
                    // Manejar el caso de respuesta no exitosa
                    // ...
                }
            }

            @Override
            public void onFailure(Call<List<Carta>> call, Throwable t) {
                // Manejar el caso de error en la llamada
                // ...
            }
        });
    }
}
