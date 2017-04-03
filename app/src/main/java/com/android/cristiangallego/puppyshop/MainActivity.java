package com.android.cristiangallego.puppyshop;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private FloatingActionButton fabCamera;
    private Toolbar tbBarraHerramientas;
    private TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciarDiseno();

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
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);
        InicializarListaDeMascotas();
        InicializarAdaptador();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.iCincoFavoritos) {
            Intent irACincoFavoritos = new Intent(this, CincoFavoritos.class);
            ArrayList<Mascota> mascotasFavoritas = new ArrayList<Mascota>();
            int contador = 0;
            for (Mascota mascota : mascotas) {
                if(mascota.isMeGusta()){
                    mascotasFavoritas.add(mascota);
                    contador++;
                }
                if(contador == 5){
                    break;
                }
            }
            irACincoFavoritos.putExtra(getResources().getString(R.string.pCincoMascotas), mascotasFavoritas);
            startActivity(irACincoFavoritos);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void instanciarDiseno() {
        this.tbBarraHerramientas = (Toolbar) findViewById(R.id.miActionBar);
        this.rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        this.fabCamera = (FloatingActionButton) findViewById(R.id.fabCamera);
        this.tvTitulo = (TextView) tbBarraHerramientas.findViewById(R.id.tvTitulo);
    }

    public void InicializarAdaptador() {
        MascotaAdaptador contactoAdaptador = new MascotaAdaptador(mascotas, this);
        rvMascotas.setAdapter(contactoAdaptador);
    }

    public void InicializarListaDeMascotas() {
        this.mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.azulado, "Perron Blanco Ojos Azules", 5, false));
        mascotas.add(new Mascota(R.drawable.hqdefault, "Trosky", 8, false));
        mascotas.add(new Mascota(R.drawable.hqdefault_dos, "Mete Lomas", 0, false));
        mascotas.add(new Mascota(R.drawable.hqdefault_tres, "Cuzco", 4, false));
        mascotas.add(new Mascota(R.drawable.hqdefault_uno, "Panela", 2, false));
        mascotas.add(new Mascota(R.drawable.labrador, "Cuca", 7, false));
        mascotas.add(new Mascota(R.drawable.peluche, "Piripitiflautica", 9, false));
    }
}
