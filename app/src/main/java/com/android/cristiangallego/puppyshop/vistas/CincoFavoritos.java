package com.android.cristiangallego.puppyshop.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.adapter.MascotaAdaptador;
import com.android.cristiangallego.puppyshop.pojo.Mascota;

import java.util.ArrayList;

public class CincoFavoritos extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private Toolbar tbBarraHerramientas;
    private TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_favoritos);

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
        MascotaAdaptador contactoAdaptador = new MascotaAdaptador(mascotas, this, this);
        rvMascotas.setAdapter(contactoAdaptador);
    }

    public void InicializarListaDeMascotas() {
        Bundle parametros = getIntent().getExtras();
        this.mascotas = (ArrayList<Mascota>)parametros.get(getResources().getString(R.string.pCincoMascotas));
    }
}
