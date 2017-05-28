package com.android.cristiangallego.puppyshop.fragmentos;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.adapter.FotoMascotaAdapter;
import com.android.cristiangallego.puppyshop.adapter.MascotaAdaptador;
import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.adapter.RestApiAdapter;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotaPerfilFragment extends Fragment {

    private RecyclerView rvFotosDeLaMascotaActual;
    private Mascota mascota;
    private CircularImageView civPerfilMascota;
    private TextView tvNombreMascota;
    private View vista;

    public MascotaPerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_mascota_perfil, container, false);

        if (mascota != null) {
            this.civPerfilMascota = (CircularImageView) vista.findViewById(R.id.civFotoMascota);
            this.tvNombreMascota = (TextView) vista.findViewById(R.id.tvNombreMascota);

            Picasso.with(vista.getContext())
                    .load(Uri.parse(mascota.getFotoPrincipalMascota().getUrl()))
                    .placeholder(R.drawable.dog_paw_icon)
                    .into(this.civPerfilMascota);

            this.tvNombreMascota.setText(mascota.getNombre());
            obtenerFotosDePerfilPropio(mascota);
        }

        return vista;
    }

    public void InicializarAdaptador() {
        FotoMascotaAdapter contactoAdaptador = new FotoMascotaAdapter(mascota.getFotosSecundarias(), getActivity());
        rvFotosDeLaMascotaActual.setAdapter(contactoAdaptador);
    }


    private void InicializarRecyclerView() {
        this.rvFotosDeLaMascotaActual = (RecyclerView) vista.findViewById(R.id.rvFotosDeLaMascotaActual);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

        /*LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);*/

        this.rvFotosDeLaMascotaActual.setLayoutManager(glm);
        InicializarAdaptador();
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void obtenerFotosDePerfilPropio(final Mascota mascota) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.construyeGsonDeserializadorSelfMediaRecent();
        IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getRecentSelfMedia();

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascota.setFotosSecundarias(contactoResponse.getFotoMascotas());
                InicializarRecyclerView();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "!Algo paso!", Toast.LENGTH_LONG).show();
                Log.e("Hay no :(", t.getMessage());
            }
        });
    }
}
