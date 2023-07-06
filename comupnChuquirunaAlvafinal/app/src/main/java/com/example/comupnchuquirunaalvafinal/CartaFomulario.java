package com.example.comupnchuquirunaalvafinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.comupnchuquirunaalvafinal.entities.Carta;
import com.example.comupnchuquirunaalvafinal.services.CartaService;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartaFomulario extends AppCompatActivity {

    private EditText edtNombreC, edtAtaque, edtDefensa;
    private static final int OPEN_CAMERA_REQUEST = 1001;
    private static final int OPEN_GALLERY_REQUEST = 1002;

    private ImageView ivAvatar;
    private String fotoEnBase64;
    private Bitmap photo;
    private String img;

    private LocationManager locationManager;

    private double latitud, longitud;

    private Button btnSave, btnGaleria, btnUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcartas);

        edtNombreC = findViewById(R.id.edtNombreC);
        edtAtaque = findViewById(R.id.edtPuntoAtaque);
        edtDefensa = findViewById(R.id.edtPuntoDefensa);
        ivAvatar = findViewById(R.id.imgPhoto);
        btnUbicacion = findViewById(R.id.btnUbi);
        btnGaleria = findViewById(R.id.btnGaleria);
        btnSave = findViewById(R.id.btnSaveC);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        List<Carta> cartaList = new ArrayList<>();
        Carta carta = new Carta();

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    LocationListener locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            latitud = location.getLatitude();
                            longitud = location.getLongitude();
                            // tvLongi.setText(longitud + "");
                            //tvLati.setText(latitud + "");
                            Log.i("MAIN_APP", "Latitud" + latitud);
                            Log.i("MAIN_APP", "Longitud" + longitud);

                            locationManager.removeUpdates(this);
                        }
                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                            // Implementación opcional si deseas manejar cambios de estado
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            // Implementación opcional si deseas manejar proveedores habilitados
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                            // Implementación opcional si deseas manejar proveedores deshabilitados
                        }
                    };

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
                }
                else{
                    String[] permissions = new String[] {Manifest.permission.ACCESS_FINE_LOCATION};
                    requestPermissions(permissions, 1000);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/";
                double latitud2= latitud;
                double longuitud2= longitud;

                carta.urlImagen = img;
                carta.nombreC = edtNombreC.getText().toString();
                carta.puntosAtaque = Double.parseDouble(edtAtaque.getText().toString());
                carta.puntosDefensa = Double.parseDouble(edtDefensa.getText().toString());
                carta.idDuelista = id;
                //anime.descripcion = edtDescription.getText().toString();
                if (latitud != 0 && longitud != 0) {
                    carta.setLatitud(latitud2);
                    carta.setLonguitud(longuitud2);
                }

                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://64a6b3f2096b3f0fcc805e89.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                CartaService service = retrofit.create(CartaService.class);

                service.create(carta).enqueue(new Callback<Void>(){

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(CartaFomulario.this, "Cambios guardados exitosamente", Toast.LENGTH_SHORT).show();// Cerrar la actividad y regresar a la actividad anterior
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 2000);
                }
            }
        });
    }
    @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == OPEN_CAMERA_REQUEST && resultCode == RESULT_OK) {
                photo = (Bitmap) data.getExtras().get("data");
                ivAvatar.setImageBitmap(photo);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                Retrofit imgRetro = new Retrofit.Builder()
                        .baseUrl("https://demo-upn.bit2bittest.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CartaService.ImageToSave imageToSave = new CartaService.ImageToSave(fotoEnBase64);

                CartaService imageService = imgRetro.create(CartaService.class);

                Call<CartaService.ImageResponse> imgC = imageService.saveImage(imageToSave);

                imgC.enqueue(new Callback<CartaService.ImageResponse>() {
                    @Override
                    public void onResponse(Call<CartaService.ImageResponse> call, Response<CartaService.ImageResponse> response) {
                        if (response.isSuccessful()) {
                            System.out.println(response.body().getUrl());
                            img = "https://demo-upn.bit2bittest.com" + response.body().getUrl();
                            //edtUrl.setText(img);
                        } else
                            Log.i("MAIN_APP", "No se subiÃ³");
                    }

                    @Override
                    public void onFailure(Call<CartaService.ImageResponse> call, Throwable t) {

                    }
                });


            }

            if (requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();

                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ivAvatar.setImageBitmap(bitmap);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    Retrofit imgRetro = new Retrofit.Builder()
                            .baseUrl("https://demo-upn.bit2bittest.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    CartaService.ImageToSave imageToSave = new CartaService.ImageToSave(fotoEnBase64);

                    CartaService imageService = imgRetro.create(CartaService.class);

                    Call<CartaService.ImageResponse> imgC = imageService.saveImage(imageToSave);

                    imgC.enqueue(new Callback<CartaService.ImageResponse>() {
                        @Override
                        public void onResponse(Call<CartaService.ImageResponse> call, Response<CartaService.ImageResponse> response) {
                            if (response.isSuccessful()) {
                                System.out.println(response.body().getUrl());
                                img = "https://demo-upn.bit2bittest.com" + response.body().getUrl();
                            } else
                                System.out.println("No subio");
                        }

                        @Override
                        public void onFailure(Call<CartaService.ImageResponse> call, Throwable t) {

                        }
                    });


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }

        }


        private void handleOpenCamera () {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                // abrir camara
                Log.i("MAIN_APP", "Tiene permisos para abrir la camara");
                abrirCamara();
            } else {
                // solicitar el permiso
                Log.i("MAIN_APP", "No tiene permisos para abrir la camara, solicitando");
                String[] permissions = new String[]{Manifest.permission.CAMERA};
                requestPermissions(permissions, 1000);
            }
        }

        private void abrirCamara () {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, OPEN_CAMERA_REQUEST);
        }

        private void openGallery () {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, OPEN_GALLERY_REQUEST);
        }
}
