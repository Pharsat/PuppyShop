package com.android.cristiangallego.puppyshop.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.bd.BaseDatos;
import com.android.cristiangallego.puppyshop.fragmentos.IMascotasPrincipalFragmentView;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.adapter.RestApiAdapter;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USUARIO on 19/04/2017.
 */

public class MascotasPrincipalFragmentPresenter implements IMascotasPrincipalFragmentPresenter {
    private IMascotasPrincipalFragmentView iMascotasPrincipalFragmentView;
    private Context context;
    private BaseDatos db;
    private ArrayList<Mascota> mascotas;

    public MascotasPrincipalFragmentPresenter(IMascotasPrincipalFragmentView iMascotasPrincipalFragmentView, Context context) {
        this.iMascotasPrincipalFragmentView = iMascotasPrincipalFragmentView;
        this.context = context;
        /*obteneMascotasBaseDeDatos();*/
        obtenerMediosRecientes();
    }

    @Override
    public void obteneMascotasBaseDeDatos() {
        this.db = new BaseDatos(context);
        mascotas = db.otenerListaPrincipal();
        mostrarMactoasRV();
    }

    @Override
    public void mostrarMactoasRV() {
        iMascotasPrincipalFragmentView.inicializarAdaptadorRV(iMascotasPrincipalFragmentView.crearAdaptador(mascotas));
        iMascotasPrincipalFragmentView.generarLayoutVertical();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.construyeGsonDeserializadorSelfMediaRecent();
        IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getRecentSelfMedia();

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getMascotas();
                mostrarMactoasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo paso!", Toast.LENGTH_LONG).show();
                Log.e("Hay no :(", t.getMessage());
            }
        });
    }
}
