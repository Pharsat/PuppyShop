package com.android.cristiangallego.puppyshop.vistas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.fragmentos.MascotasPrincipalFragment;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.ConstantesRestApi;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.adapter.RestApiAdapter;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurarCuenta extends AppCompatActivity {

    private ArrayList<Mascota> clientsIdResultantes;
    private Button bBuscar;
    private EditText etCuenta;

    private FloatingActionButton fabCamera;
    private Toolbar tbBarraHerramientas;
    private TextView tvTitulo;

    private Activity actividad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        clientsIdResultantes = new ArrayList<>();
        instanciarDiseno();

        setSupportActionBar(this.tbBarraHerramientas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dog_paw_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.tvTitulo.setText(getTitle());

        this.fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        this.bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerIdPorUsuario();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide efectoSlide = new Slide(Gravity.TOP);
            efectoSlide.setDuration(5000);
            getWindow().setEnterTransition(efectoSlide);
            //startActivity(actividad, ActivityOptionsCompat.makeSceneTransitionAnimation(this, this.findViewById(android.R.id.content), "").toBundle());
        }
    }

    public void instanciarDiseno() {
        this.bBuscar = (Button) findViewById(R.id.bConfigura);
        this.etCuenta = (EditText) findViewById(R.id.nombreDeUsuario);
        this.tbBarraHerramientas = (Toolbar) findViewById(R.id.miActionBar);
        this.fabCamera = (FloatingActionButton) findViewById(R.id.fabCamera);
        this.tvTitulo = (TextView) tbBarraHerramientas.findViewById(R.id.tvTitulo);
        this.actividad = this;
    }

    public void obtenerIdPorUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.construyeGsonDeserializadorSearchInfo();
        IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getSearchInformation(ConstantesRestApi.URL_GET_SEARCH_INFO.replace("{query}", etCuenta.getText().toString()));

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                ArrayList<Mascota> mascotas = contactoResponse.getMascotas();
                for (Mascota mascota : mascotas) {
                    clientsIdResultantes.add(mascota);
                }
                if (clientsIdResultantes.size() > 0) {
                    Intent intento = new Intent(actividad, MainActivity.class);
                    intento.putExtra(getResources().getString(R.string.perfilACargar), clientsIdResultantes.get(0));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Slide efectoSlide = new Slide();
                        efectoSlide.setDuration(12000);
                        getWindow().setExitTransition(efectoSlide);
                        startActivity(intento, ActivityOptionsCompat.makeSceneTransitionAnimation(actividad, actividad.findViewById(android.R.id.content), "").toBundle());
                    } else {
                        startActivity(intento);
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Lo sentimos, no hay ningun resultado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "!Algo paso!", Toast.LENGTH_LONG).show();
                Log.e("Hay no :(", t.getMessage());
            }
        });
    }
}
