package com.android.cristiangallego.puppyshop.vistas;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.adapter.MascotaAdaptador;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.adapter.RestApiAdapter;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CincoFavoritos extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private Toolbar tbBarraHerramientas;
    private TextView tvTitulo;
    Mascota miPropioUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_favoritos);


        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Call<MascotaResponse> contactoResponseCall;
        Gson gson = restApiAdapter.construyeGsonDeserializadorSelfInfo();
        IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
        contactoResponseCall = endpointsApi.getSelfInformation();
        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                miPropioUsuario = contactoResponse.getMascotas().get(0);
                continuar();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "!Algo paso!", Toast.LENGTH_LONG).show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide efectoSlide = new Slide(Gravity.TOP);
            efectoSlide.setDuration(5000);
            getWindow().setEnterTransition(efectoSlide);
            //startActivity(actividad, ActivityOptionsCompat.makeSceneTransitionAnimation(this, this.findViewById(android.R.id.content), "").toBundle());
        }
    }

    private void continuar(){
        instanciarDiseno();

        setSupportActionBar(this.tbBarraHerramientas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dog_paw_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        this.tvTitulo.setText(getTitle());

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);
        InicializarListaDeMascotas();
        InicializarAdaptador();
    }

    public void instanciarDiseno() {
        this.tbBarraHerramientas = (Toolbar) findViewById(R.id.miActionBar);
        this.rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        this.tvTitulo = (TextView) tbBarraHerramientas.findViewById(R.id.tvTitulo);
    }

    public void InicializarAdaptador() {
        MascotaAdaptador contactoAdaptador = new MascotaAdaptador(mascotas, this, this, miPropioUsuario);
        rvMascotas.setAdapter(contactoAdaptador);
    }

    public void InicializarListaDeMascotas() {
        Bundle parametros = getIntent().getExtras();
        this.mascotas = (ArrayList<Mascota>)parametros.get(getResources().getString(R.string.pCincoMascotas));
    }
}
