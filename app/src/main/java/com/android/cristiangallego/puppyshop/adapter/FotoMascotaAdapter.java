package com.android.cristiangallego.puppyshop.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by USUARIO on 9/04/2017.
 */

public class FotoMascotaAdapter extends RecyclerView.Adapter<FotoMascotaAdapter.FotoMascotaViewHolder> {

    ArrayList<FotoMascota> fotosDeMascota;
    Activity actividad;

    public FotoMascotaAdapter(ArrayList<FotoMascota> fotosDeMascota, Activity actividad) {
        this.fotosDeMascota = fotosDeMascota;
        this.actividad = actividad;
    }

    @Override
    public FotoMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotomascota, parent, false);
        return new FotoMascotaAdapter.FotoMascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FotoMascotaViewHolder holder, int position) {
        FotoMascota fotoMascota = fotosDeMascota.get(position);
        pintarElementos(holder, fotoMascota);
    }

    @Override
    public int getItemCount() {
        return fotosDeMascota.size();
    }

    public static class FotoMascotaViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFotoMascota;
        TextView tvRaitingMascota;
        ImageButton ibLike;

        public FotoMascotaViewHolder(View itemView) {
            super(itemView);
            ivFotoMascota = (ImageView) itemView.findViewById(R.id.ivFotoMascota);
            tvRaitingMascota = (TextView) itemView.findViewById(R.id.tvRaitingMascota);
        }
    }

    private void pintarElementos(FotoMascotaAdapter.FotoMascotaViewHolder holder, FotoMascota fotoMascota) {
        holder.ivFotoMascota.setImageResource(fotoMascota.getNroFoto());
        holder.tvRaitingMascota.setText(String.valueOf(fotoMascota.getNroLikes()));

        Picasso.with(actividad)
                .load(Uri.parse(fotoMascota.getUrl()))
                .placeholder(R.drawable.dog_paw_icon)
                .into(holder.ivFotoMascota);
    }
}
