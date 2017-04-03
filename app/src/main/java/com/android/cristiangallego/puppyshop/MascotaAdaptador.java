package com.android.cristiangallego.puppyshop;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USUARIO on 2/04/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity actividad;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity actividad) {
        this.mascotas = mascotas;
        this.actividad = actividad;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        pintarElementos(holder, mascota);

        holder.ibLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean nuevoMegusta = !mascota.isMeGusta();
                mascota.setMeGusta(nuevoMegusta);
                if (nuevoMegusta) {
                    mascota.setRating(mascota.getRating() + 1);
                } else {
                    mascota.setRating(mascota.getRating() - 1);
                }
                pintarElementos(holder, mascota);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    private void pintarElementos(MascotaViewHolder holder, Mascota mascota) {
        holder.ivFotoMascota.setImageResource(mascota.getFotoMascota());
        holder.tvNombreMascota.setText(mascota.getNombre());
        holder.tvRaitingMascota.setText(String.valueOf(mascota.getRating()));
        if (mascota.isMeGusta()) {
            holder.ibLike.setImageResource(R.drawable.dog_bone_filled_50);
        } else {
            holder.ibLike.setImageResource(R.drawable.dog_bone_50);
        }
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFotoMascota;
        TextView tvNombreMascota;
        TextView tvRaitingMascota;
        ImageButton ibLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            ivFotoMascota = (ImageView) itemView.findViewById(R.id.ivFotoMascota);
            tvNombreMascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);
            tvRaitingMascota = (TextView) itemView.findViewById(R.id.tvRaitingMascota);
            ibLike = (ImageButton) itemView.findViewById(R.id.ibLike);
        }
    }
}
