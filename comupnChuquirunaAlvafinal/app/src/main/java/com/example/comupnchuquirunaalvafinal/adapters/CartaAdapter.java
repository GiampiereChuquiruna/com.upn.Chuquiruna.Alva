package com.example.comupnchuquirunaalvafinal.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupnchuquirunaalvafinal.MapsActivity;
import com.example.comupnchuquirunaalvafinal.R;
import com.example.comupnchuquirunaalvafinal.entities.Carta;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartaAdapter extends RecyclerView.Adapter<CartaAdapter.CartaViewHolder>{
    List<Carta> items;

    public CartaAdapter(List<Carta> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CartaAdapter.CartaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_carta, parent, false);
        CartaAdapter.CartaViewHolder viewHolder = new CartaAdapter.CartaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartaAdapter.CartaViewHolder holder, int position) {
        Carta item = items.get(position);
        View view = holder.itemView;

        TextView tvTipo = view.findViewById(R.id.tvCartaN);
        tvTipo.setText(item.nombreC);

        TextView tvAtaque = view.findViewById(R.id.tvAtaque);
        tvAtaque.setText(String.valueOf(item.puntosAtaque));

        TextView tvDefensa = view.findViewById(R.id.tvDefensa);
        tvDefensa.setText(String.valueOf(item.puntosDefensa));


        ImageView imgFoto = view.findViewById(R.id.imgCarta);
        Picasso.get().load(item.urlImagen).into(imgFoto);

        Button btnVerMapa = view.findViewById(R.id.btnVerMapa);
        btnVerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir el nuevo Activity
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("latitud", items.get(position).getLatitud());
                intent.putExtra("longitud", items.get(position).getLonguitud());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CartaViewHolder extends RecyclerView.ViewHolder {

        public CartaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
