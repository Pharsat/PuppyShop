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

    private String idClienteACargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciarDiseno();

        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            if (parametros.containsKey(getResources().getString(R.string.perfilACargar))) {
                this.idClienteACargar = parametros.getString(getResources().getString(R.string.pCincoMascotas));
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
       /* this.mascotas = new ArrayList<>();

        ArrayList<FotoMascota> fotosSecundarias1 = new ArrayList<>();
        fotosSecundarias1.add(new FotoMascota(R.drawable.azulado, 1));
        fotosSecundarias1.add(new FotoMascota(R.drawable.azulado, 2));
        fotosSecundarias1.add(new FotoMascota(R.drawable.azulado, 5));
        fotosSecundarias1.add(new FotoMascota(R.drawable.azulado, 8));
        fotosSecundarias1.add(new FotoMascota(R.drawable.azulado, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.azulado, 5), "Perron Blanco Ojos Azules", 5, false, fotosSecundarias1));

        ArrayList<FotoMascota> fotosSecundarias2 = new ArrayList<>();
        fotosSecundarias2.add(new FotoMascota(R.drawable.hqdefault, 1));
        fotosSecundarias2.add(new FotoMascota(R.drawable.hqdefault, 2));
        fotosSecundarias2.add(new FotoMascota(R.drawable.hqdefault, 5));
        fotosSecundarias2.add(new FotoMascota(R.drawable.hqdefault, 8));
        fotosSecundarias2.add(new FotoMascota(R.drawable.hqdefault, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.hqdefault, 8), "Trosky", 8, false, fotosSecundarias2));

        ArrayList<FotoMascota> fotosSecundarias3 = new ArrayList<>();
        fotosSecundarias3.add(new FotoMascota(R.drawable.hqdefault_dos, 1));
        fotosSecundarias3.add(new FotoMascota(R.drawable.hqdefault_dos, 2));
        fotosSecundarias3.add(new FotoMascota(R.drawable.hqdefault_dos, 5));
        fotosSecundarias3.add(new FotoMascota(R.drawable.hqdefault_dos, 8));
        fotosSecundarias3.add(new FotoMascota(R.drawable.hqdefault_dos, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.hqdefault_dos, 0), "Mete Lomas", 0, false, fotosSecundarias3));

        ArrayList<FotoMascota> fotosSecundarias4 = new ArrayList<>();
        fotosSecundarias4.add(new FotoMascota(R.drawable.hqdefault_tres, 1));
        fotosSecundarias4.add(new FotoMascota(R.drawable.hqdefault_tres, 2));
        fotosSecundarias4.add(new FotoMascota(R.drawable.hqdefault_tres, 5));
        fotosSecundarias4.add(new FotoMascota(R.drawable.hqdefault_tres, 8));
        fotosSecundarias4.add(new FotoMascota(R.drawable.hqdefault_tres, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.hqdefault_tres, 4), "Cuzco", 4, false, fotosSecundarias4));

        ArrayList<FotoMascota> fotosSecundarias5 = new ArrayList<>();
        fotosSecundarias5.add(new FotoMascota(R.drawable.hqdefault_uno, 1));
        fotosSecundarias5.add(new FotoMascota(R.drawable.hqdefault_uno, 2));
        fotosSecundarias5.add(new FotoMascota(R.drawable.hqdefault_uno, 5));
        fotosSecundarias5.add(new FotoMascota(R.drawable.hqdefault_uno, 8));
        fotosSecundarias5.add(new FotoMascota(R.drawable.hqdefault_uno, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.hqdefault_uno, 2), "Panela", 2, false, fotosSecundarias5));

        ArrayList<FotoMascota> fotosSecundarias6 = new ArrayList<>();
        fotosSecundarias6.add(new FotoMascota(R.drawable.labrador, 1));
        fotosSecundarias6.add(new FotoMascota(R.drawable.labrador, 2));
        fotosSecundarias6.add(new FotoMascota(R.drawable.labrador, 5));
        fotosSecundarias6.add(new FotoMascota(R.drawable.labrador, 8));
        fotosSecundarias6.add(new FotoMascota(R.drawable.labrador, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.labrador, 7), "Cuca", 7, false, fotosSecundarias6));

        ArrayList<FotoMascota> fotosSecundarias7 = new ArrayList<>();
        fotosSecundarias7.add(new FotoMascota(R.drawable.peluche, 1));
        fotosSecundarias7.add(new FotoMascota(R.drawable.peluche, 2));
        fotosSecundarias7.add(new FotoMascota(R.drawable.peluche, 5));
        fotosSecundarias7.add(new FotoMascota(R.drawable.peluche, 8));
        fotosSecundarias7.add(new FotoMascota(R.drawable.peluche, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.peluche, 9), "Piripitiflautica", 9, false, fotosSecundarias7));

        ArrayList<FotoMascota> fotosSecundariasJuancho = new ArrayList<>();
        fotosSecundariasJuancho.add(new FotoMascota(R.drawable.juancho, 1));
        fotosSecundariasJuancho.add(new FotoMascota(R.drawable.juancho, 2));
        fotosSecundariasJuancho.add(new FotoMascota(R.drawable.juancho, 5));
        fotosSecundariasJuancho.add(new FotoMascota(R.drawable.juancho, 8));
        fotosSecundariasJuancho.add(new FotoMascota(R.drawable.juancho, 9));
        mascotas.add(new Mascota(new FotoMascota(R.drawable.juancho, 9), "Juancho", 9, false, fotosSecundariasJuancho));*/
    }

    public void obtenerPerfilPropio() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Call<MascotaResponse> contactoResponseCall;
        if (this.idClienteACargar != null && !this.idClienteACargar.isEmpty() && !this.idClienteACargar.equals("null")) {
            Gson gson = restApiAdapter.construyeGsonDeserializadorUserMediaRecent();
            IEndpointApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);
            contactoResponseCall = endpointsApi.getRecentUserMedia(ConstantesRestApi.URL_GET_USER_RECENT_MEDIA.replace("{user-id}", this.idClienteACargar));
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
