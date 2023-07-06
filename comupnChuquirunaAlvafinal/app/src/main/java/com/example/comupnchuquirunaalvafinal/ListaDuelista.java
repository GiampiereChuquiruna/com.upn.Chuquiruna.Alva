package com.example.comupnchuquirunaalvafinal;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupnchuquirunaalvafinal.adapters.DuelistaAdapter;
import com.example.comupnchuquirunaalvafinal.entities.Duelista;
import com.example.comupnchuquirunaalvafinal.services.DuelistaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDuelista extends AppCompatActivity {

    public RecyclerView rvListaDuelista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaduelista);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64a6b3f2096b3f0fcc805e89.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DuelistaService service = retrofit.create(DuelistaService.class);
        service.getAll().enqueue(new Callback<List<Duelista>>() {
            @Override
            public void onResponse(Call<List<Duelista>> call, Response<List<Duelista>> response) {
                List<Duelista> items = response.body();
                Log.d("ListaDuelista", "Cantidad de items: " + items.size());
                rvListaDuelista = findViewById(R.id.rvListaDulista);
                rvListaDuelista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvListaDuelista.setAdapter(new DuelistaAdapter(items));
            }

            @Override
            public void onFailure(Call<List<Duelista>> call, Throwable t) {

            }
        });
    }
}
