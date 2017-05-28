package com.android.cristiangallego.puppyshop.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.bd.BaseDatos;
import com.android.cristiangallego.puppyshop.fragmentos.IMascotasPrincipalFragmentView;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.ConstantesRestApi;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.adapter.RestApiAdapter;
import com.android.cristiangallego.puppyshop.restApi.deserializador.AmigosDeserializador;
import com.android.cristiangallego.puppyshop.restApi.model.AmigosResponse;
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
    private ArrayList<String> losQueYoSigo;

    public MascotasPrincipalFragmentPresenter(IMascotasPrincipalFragmentView iMascotasPrincipalFragmentView, Context context) {
        this.iMascotasPrincipalFragmentView = iMascotasPrincipalFragmentView;
        this.context = context;
        /*obteneMascotasBaseDeDatos();*/
        /*consultarLosQueYoSigo*/
        /*obtenerMediosRecientes();*/
        consultarALosQueYoSigo();
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
    public void obtenerMediosRecientes(final ArrayList<String> pendientesACargar) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.construyeGsonDeserializadorUserMediaRecent();
        IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getRecentUserMedia(ConstantesRestApi.URL_GET_USER_RECENT_MEDIA.replace("{user-id}", pendientesACargar.get(0)));

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                for(Mascota mascota: contactoResponse.getMascotas()){
                    mascotas.add(mascota);
                }
                if(pendientesACargar.size() == 1) {
                    mostrarMactoasRV();
                } else {
                    pendientesACargar.remove(0);
                    obtenerMediosRecientes(pendientesACargar);
                }

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo paso!", Toast.LENGTH_LONG).show();
                Log.e("Hay no :(", t.getMessage());
            }
        });
    }

    @Override
    public void consultarALosQueYoSigo() {
        mascotas = new ArrayList<>();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.construyeGsonDeserializadorUserFollowed();
        IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
        Call<AmigosResponse> contactoResponseCall = endpointsApi.getFolloweds();

        contactoResponseCall.enqueue(new Callback<AmigosResponse>() {
            @Override
            public void onResponse(Call<AmigosResponse> call, Response<AmigosResponse> response) {
                AmigosResponse contactoResponse = response.body();
                losQueYoSigo = contactoResponse.getClientesId();
                if(losQueYoSigo.size()> 0) {
                    obtenerMediosRecientes(losQueYoSigo);
                } else {
                    mostrarMactoasRV();
                }
            }

            @Override
            public void onFailure(Call<AmigosResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo paso!", Toast.LENGTH_LONG).show();
                Log.e("Hay no :(", t.getMessage());
            }
        });
    }
}
