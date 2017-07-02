package com.android.cristiangallego.puppyshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.bd.BaseDatos;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.ConstantesRestApi;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.adapter.RestApiAdapter;
import com.android.cristiangallego.puppyshop.restApi.model.BasicResponse;
import com.android.cristiangallego.puppyshop.restApi.model.LikeInstagramResponse;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.android.cristiangallego.puppyshop.restApi.model.UsuarioResponse;
import com.android.cristiangallego.puppyshop.vistas.MainActivity;
import com.android.cristiangallego.puppyshop.vistas.RecibirNotificaciones;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USUARIO on 2/04/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity actividad;
    Context context;
    Mascota miPropioUsuario;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, final Activity actividad, Context context, Mascota miPropioUsuario) {
        this.mascotas = mascotas;
        this.actividad = actividad;
        this.context = context;
        this.miPropioUsuario = miPropioUsuario;
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

                //BaseDatos db = new BaseDatos(context);
                // insertarContactos(db);
                //db.darLike(mascota.getId(), nuevoMegusta, mascota.getFotoPrincipalMascota().getId());

                if (nuevoMegusta) {
                    mascota.getFotoPrincipalMascota().setNroLikes(mascota.getFotoPrincipalMascota().getNroLikes() + 1);
                    // Log and toast
                    String token = FirebaseInstanceId.getInstance().getToken();
                    RestApiAdapter restApiAdapter = new RestApiAdapter();
                    IEndpointApi endpoints = restApiAdapter.establecerConexionRestAPIHeroku();
                    while (miPropioUsuario == null) {
                        //nothing just wait.
                    }
                    Call<LikeInstagramResponse> usuarioResponseCall = endpoints.darLikeInstagram(token, mascota.getId(), mascota.getFotoPrincipalMascota().getId(), miPropioUsuario.getId());
                    usuarioResponseCall.enqueue(new Callback<LikeInstagramResponse>() {
                        @Override
                        public void onResponse(Call<LikeInstagramResponse> call, Response<LikeInstagramResponse> response) {
                            LikeInstagramResponse usuarioResponse = response.body();
                            Toast.makeText(actividad, "Se almaceno el like en firebase con exito", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<LikeInstagramResponse> call, Throwable t) {
                            Toast.makeText(actividad, "Fallo :(", Toast.LENGTH_SHORT).show();
                        }
                    });

                    RestApiAdapter restApiAdapter2 = new RestApiAdapter();
                    IEndpointApi endpoints2 = restApiAdapter2.establecerConexionRestApiInstagram();
                    Call<BasicResponse> usuarioResponseCall2 = endpoints2.getLike(ConstantesRestApi.URL_GET_LIKE.replace("{media-id}", mascota.getFotoPrincipalMascota().getId()), ConstantesRestApi.ACCESS_TOKEN);
                    usuarioResponseCall2.enqueue(new Callback<BasicResponse>() {
                        @Override
                        public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                            BasicResponse contactoResponse = response.body();
                            Toast.makeText(actividad, "Se ha enviado el like a instagram correctamente", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<BasicResponse> call, Throwable t) {
                            Toast.makeText(actividad, "!Algo paso!", Toast.LENGTH_LONG).show();
                            Log.e("Hay no :(", t.getMessage());
                        }
                    });

                    enviarNotificacion(mascota.getId());

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
        holder.tvNombreMascota.setText(mascota.getNombre());
        holder.tvRaitingMascota.setText(String.valueOf(mascota.getFotoPrincipalMascota().getNroLikes()));
        if (mascota.isMeGusta()) {
            holder.ibLike.setImageResource(R.drawable.dog_bone_filled_50);
        } else {
            holder.ibLike.setImageResource(R.drawable.dog_bone_50);
        }

        Picasso.with(actividad)
                .load(Uri.parse(mascota.getFotoPrincipalMascota().getUrl()))
                .placeholder(R.drawable.dog_paw_icon)
                .into(holder.ivFotoMascota);
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

    private void enviarNotificacion(String IdDestinatario) {
        RestApiAdapter restApiAdapter2 = new RestApiAdapter();
        IEndpointApi endpoints2 = restApiAdapter2.establecerConexionRestApiInstagram();
        Call<LikeInstagramResponse[]> usuarioResponseCall2 = endpoints2.notificarALaPersona(ConstantesRestApi.KEY_POST_NOTIFICAR_PERSONA.replace("{id-dispositivo}", IdDestinatario));
        usuarioResponseCall2.enqueue(new Callback<LikeInstagramResponse[]>() {
            @Override
            public void onResponse(Call<LikeInstagramResponse[]> call, Response<LikeInstagramResponse[]> response) {
                LikeInstagramResponse[] contactoResponse = response.body();
                if (contactoResponse.length > 0) {
                    for (LikeInstagramResponse respuesta : contactoResponse) {
                        Toast.makeText(actividad, "Se ha notificado al usuario, " + respuesta.getId_usuario_instagram(), Toast.LENGTH_SHORT).show();
                    }
                } else                 {
                    Toast.makeText(actividad, "El usuario al que le diste like no existia en mi BD :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LikeInstagramResponse[]> call, Throwable t) {
                Toast.makeText(actividad, "!Algo paso!", Toast.LENGTH_LONG).show();
                Log.e("Hay no :(", t.getMessage());
            }
        });
    }
}
