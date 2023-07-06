package com.example.comupnchuquirunaalvafinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comupnchuquirunaalvafinal.entities.Duelista;
import com.example.comupnchuquirunaalvafinal.services.DuelistaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DuelistaFormulario extends AppCompatActivity {

    private Button btnSave;
    private EditText edtNombreD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formduelista);

        edtNombreD = findViewById(R.id.edtNameDuelista);
        btnSave = findViewById(R.id.btnSaveD);

        List<Duelista> duelistaList = new ArrayList<>();
        Duelista duelista = new Duelista();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://64a6b3f2096b3f0fcc805e89.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                DuelistaService service = retrofit.create(DuelistaService.class);
                service.create(duelista).enqueue(new Callback<Void>(){

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(DuelistaFormulario.this, "Cambios guardados exitosamente", Toast.LENGTH_SHORT).show();// Cerrar la actividad y regresar a la actividad anterior
                        Intent intent =  new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }
}
