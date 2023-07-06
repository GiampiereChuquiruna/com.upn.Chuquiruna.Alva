package com.example.comupnchuquirunaalvafinal.services;

import com.example.comupnchuquirunaalvafinal.entities.Carta;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CartaService {
    @GET("carta")
    Call<List<Carta>> getAll(@Query("idDuelista") int idDuelista);
    @POST("carta")
    Call<Void> create(@Body Carta carta);


    @PUT("carta/{id}")
    Call<Void> update(@Path("id") int id, @Body Carta carta);

    @DELETE("carta/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("image")
    Call<ImageResponse> saveImage(@Body ImageToSave image);
    class ImageResponse {
        @SerializedName("url")
        private String url;

        public String getUrl(){
            return url;
        }
    }
    class ImageToSave {
        String base64Image;

        public ImageToSave(String base64Image){
            this.base64Image = base64Image;
        }
    }
}
