package com.android.cristiangallego.puppyshop.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.cristiangallego.puppyshop.R;

public class Biografia extends AppCompatActivity {

    private Toolbar tbBarraHerramientas;
    private TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografia);

        this.tbBarraHerramientas = (Toolbar) findViewById(R.id.miActionBar);
        this.tvTitulo = (TextView)tbBarraHerramientas.findViewById(R.id.tvTitulo);

        setSupportActionBar(this.tbBarraHerramientas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dog_paw_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.tvTitulo.setText(getTitle());
    }
}
