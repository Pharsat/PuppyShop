package com.android.cristiangallego.puppyshop.vistas;

import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
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
        this.tvTitulo = (TextView) tbBarraHerramientas.findViewById(R.id.tvTitulo);

        setSupportActionBar(this.tbBarraHerramientas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dog_paw_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.tvTitulo.setText(getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide efectoSlide = new Slide(Gravity.TOP);
            efectoSlide.setDuration(5000);
            getWindow().setEnterTransition(efectoSlide);
            //startActivity(actividad, ActivityOptionsCompat.makeSceneTransitionAnimation(this, this.findViewById(android.R.id.content), "").toBundle());
        }
    }
}
