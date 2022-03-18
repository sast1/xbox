package com.example.version1.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version1.R;
import com.example.version1.roles.Comprador;

import java.util.ArrayList;
import java.util.Collections;

public class adaptadorHistorial extends RecyclerView.Adapter<adaptadorHistorial.CompradorViewholder> {

    ArrayList<Comprador> listaComponentes;


    public adaptadorHistorial (ArrayList<Comprador> listaComponentes){

        this.listaComponentes = listaComponentes;
    }


    @NonNull
    @Override
    public CompradorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comprasmodelo, null, false);

        return new CompradorViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompradorViewholder holder, int position) {


        holder.nombrecomprador.setText(listaComponentes.get(position).getComprador());
        holder.gamilcomprador.setText(listaComponentes.get(position).getGmailcom());
        holder.cedulacomprador.setText(listaComponentes.get(position).getCedulacom());
        holder.telefonocomprador.setText(listaComponentes.get(position).getTelefonocom());
        holder.nombrejj.setText(listaComponentes.get(position).getJuego());
        holder.costo.setText(listaComponentes.get(position).getCosto());


    }

    @Override
    public int getItemCount() {
        return listaComponentes.size();
    }

    public class CompradorViewholder extends RecyclerView.ViewHolder {


        TextView nombrecomprador, gamilcomprador, cedulacomprador, telefonocomprador, costo, nombrejj;


        public CompradorViewholder(@NonNull View itemView) {
            super(itemView);

            nombrecomprador=itemView.findViewById(R.id.nombrecomprador);
            gamilcomprador=itemView.findViewById(R.id.gamilcomprador);
            cedulacomprador=itemView.findViewById(R.id.cedulacomprador);
            telefonocomprador=itemView.findViewById(R.id.telefonocomprador);
            nombrejj=itemView.findViewById(R.id.nombrejj);
            costo=itemView.findViewById(R.id.costojuego);


        }
    }
}
