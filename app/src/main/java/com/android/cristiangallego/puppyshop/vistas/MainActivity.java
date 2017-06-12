package com.android.cristiangallego.puppyshop.vistas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.adapter.PageAdapter;
import com.android.cristiangallego.puppyshop.bd.BaseDatos;
import com.android.cristiangallego.puppyshop.fragmentos.MascotaPerfilFragment;
import com.android.cristiangallego.puppyshop.fragmentos.MascotasPrincipalFragment;
import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
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

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabCamera;
    private ArrayList<Mascota> mascotas;
    private Toolbar tbBarraHerramientas;
    private TextView tvTitulo;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MascotasPrincipalFragment perritosFragment;
    ArrayList<Fragment> fragments;

    public MascotaPerfilFragment getPerritoFragment() {
        return perritoFragment;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    private MascotaPerfilFragment perritoFragment;

    private Mascota idClienteACargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciarDiseno();

        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            if (parametros.containsKey(getResources().getString(R.string.perfilACargar))) {
                this.idClienteACargar = (Mascota)parametros.get(getResources().getString(R.string.perfilACargar));
            }
        }

        obtenerPerfilPropio();
    }

    private void ContinuarCargaDeInformacion() {
        setUpViewPager();

        setSupportActionBar(this.tbBarraHerramientas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dog_paw_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.tvTitulo.setText(getTitle());

        this.fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intento = null;
        switch (item.getItemId()) {
            case R.id.iCincoFavoritos:
                intento = new Intent(this, CincoFavoritos.class);
                ArrayList<Mascota> mascotasFavoritas = new ArrayList<Mascota>();
                int contador = 0;
                for (Mascota mascota : mascotas) {
                    if (mascota.isMeGusta()) {
                        mascotasFavoritas.add(mascota);
                        contador++;
                    }
                    if (contador == 5) {
                        break;
                    }
                }
                intento.putExtra(getResources().getString(R.string.pCincoMascotas), mascotasFavoritas);
                break;
            case R.id.mContacto:
                intento = new Intent(this, Contacto.class);
                break;
            case R.id.mAcercaDe:
                intento = new Intent(this, Biografia.class);
                break;
            case R.id.configurarPerfil:
                intento = new Intent(this, ConfigurarCuenta.class);
                break;
            case R.id.recivirNotificaciones:
                intento = new Intent(this, RecibirNotificaciones.class);
                intento.putExtra(getResources().getString(R.string.perfilACargar), this.idClienteACargar);
                break;
        }
        if (intento != null) {
            startActivity(intento);
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments() {
        this.fragments = new ArrayList<>();
        this.perritosFragment = new MascotasPrincipalFragment();
        this.perritosFragment.setMascotas(mascotas);
        this.perritoFragment = new MascotaPerfilFragment();
        this.perritoFragment.setMascota(mascotas.get(mascotas.size() - 1));
        fragments.add(this.perritosFragment);
        fragments.add(this.perritoFragment);
        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dog_house);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_face);
    }

    public void instanciarDiseno() {
        this.tbBarraHerramientas = (Toolbar) findViewById(R.id.miActionBar);
        this.fabCamera = (FloatingActionButton) findViewById(R.id.fabCamera);
        this.tvTitulo = (TextView) tbBarraHerramientas.findViewById(R.id.tvTitulo);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    public void InicializarListaDeMascotas() {

        BaseDatos db = new BaseDatos(this);
        // insertarContactos(db);
        /*this.mascotas = db.otenerListaPrincipal();*/
        this.mascotas = new ArrayList<>();
       /* this.mascotas = new ArrayList<>();*/
    }

    public void obtenerPerfilPropio() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Call<MascotaResponse> contactoResponseCall;
        if (this.idClienteACargar != null && this.idClienteACargar.getId() != null && !this.idClienteACargar.getId().isEmpty() && !this.idClienteACargar.getId().equals("null")) {
            Gson gson = restApiAdapter.construyeGsonDeserializadorUserMediaRecent();
            IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
            contactoResponseCall = endpointsApi.getRecentUserMedia(ConstantesRestApi.URL_GET_USER_RECENT_MEDIA.replace("{user-id}", this.idClienteACargar.getId()));
        } else {
            Gson gson = restApiAdapter.construyeGsonDeserializadorSelfInfo();
            IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
            contactoResponseCall = endpointsApi.getSelfInformation();
        }

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getMascotas();
                ContinuarCargaDeInformacion();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "!Algo paso!", Toast.LENGTH_LONG).show();
                Log.e("Hay no :(", t.getMessage());
            }
        });
    }
}
