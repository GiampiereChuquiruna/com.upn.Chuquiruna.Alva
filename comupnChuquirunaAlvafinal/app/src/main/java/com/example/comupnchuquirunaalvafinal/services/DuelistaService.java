package com.example.comupnchuquirunaalvafinal.services;

import com.example.comupnchuquirunaalvafinal.entities.Duelista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DuelistaService {

    @GET("duelista")
    Call<List<Duelista>> getAll();

    @POST("duelista")
    Call<Void> create(@Body Duelista duelista);

    @GET("duelista/{id}")
    Call<Duelista> findAnime(@Path("id") int id);
}
