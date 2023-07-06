package com.example.comupnchuquirunaalvafinal.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupnchuquirunaalvafinal.MainCartas;
import com.example.comupnchuquirunaalvafinal.R;
import com.example.comupnchuquirunaalvafinal.entities.Duelista;

import java.util.List;

public class DuelistaAdapter extends RecyclerView.Adapter<DuelistaAdapter.DuelistaViewHolder>{

    List<Duelista> items;
    public DuelistaAdapter(List<Duelista> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DuelistaAdapter.DuelistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_listaduelista, parent, false);
        DuelistaViewHolder viewHolder = new DuelistaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DuelistaAdapter.DuelistaViewHolder holder, int position) {
        Duelista item = items.get(position);
        View view = holder.itemView;

        TextView tvNameDuelista = view.findViewById(R.id.tvNameDuelista);
        tvNameDuelista.setText(item.nombre);

        tvNameDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), MainCartas.class);
                intent.putExtra("nombre", items.get(position).getNombre());
                intent.putExtra("id", items.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class DuelistaViewHolder extends RecyclerView.ViewHolder {

        public DuelistaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
