package com.android.cristiangallego.puppyshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.bd.BaseDatos;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.vistas.MainActivity;

import java.util.ArrayList;

/**
 * Created by USUARIO on 2/04/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity actividad;
    Context context;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity actividad, Context context) {
        this.mascotas = mascotas;
        this.actividad = actividad;
        this.context = context;
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

        /*holder.ivFotoMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)actividad).getPerritoFragment().RepintarPantalla(mascota);
                ((MainActivity)actividad).getTabLayout().getTabAt(1).select();
            }
        });*/
        holder.ibLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean nuevoMegusta = !mascota.isMeGusta();
                mascota.setMeGusta(nuevoMegusta);

                BaseDatos db = new BaseDatos(context);
                // insertarContactos(db);
                db.darLike(mascota.getId(),nuevoMegusta,mascota.getFotoPrincipalMascota().getId());

                if (nuevoMegusta) {
                    mascota.getFotoPrincipalMascota().setNroLikes(mascota.getFotoPrincipalMascota().getNroLikes() + 1);
                } else {
                    mascota.getFotoPrincipalMascota().setNroLikes(mascota.getFotoPrincipalMascota().getNroLikes() - 1);
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
        holder.ivFotoMascota.setImageResource(mascota.getFotoPrincipalMascota().getNroFoto());
        holder.tvNombreMascota.setText(mascota.getNombre());
        holder.tvRaitingMascota.setText(String.valueOf(mascota.getFotoPrincipalMascota().getNroLikes()));
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
