package com.android.cristiangallego.puppyshop.vistas;

import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.pojo.ElementosEnvioCorreo;
import com.android.cristiangallego.puppyshop.asincronos.EnviarCorreoAsyncTask;

public class Contacto extends AppCompatActivity {

    private TextInputEditText txtNombre;
    private TextInputEditText txtCorreo;
    private TextInputEditText txtMensaje;
    private Toolbar tbBarraHerramientas;
    private TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        this.txtNombre = (TextInputEditText) findViewById(R.id.txtNombre);
        this.txtCorreo = (TextInputEditText) findViewById(R.id.txtCorreo);
        this.txtMensaje = (TextInputEditText) findViewById(R.id.txtMensaje);
        this.tbBarraHerramientas = (Toolbar) findViewById(R.id.miActionBar);
        this.tvTitulo = (TextView)tbBarraHerramientas.findViewById(R.id.tvTitulo);

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

    public void btnEnviarContacto_Clic(View view) {
        try {
            ElementosEnvioCorreo elementos = new ElementosEnvioCorreo(txtCorreo.getText().toString(),
                    txtMensaje.getText().toString(),
                    txtNombre.getText().toString());
            EnviarCorreoAsyncTask tareaEnviarCorreo = new EnviarCorreoAsyncTask();
            Boolean resultado = tareaEnviarCorreo.execute(elementos).get();
            if (resultado) {
                Toast.makeText(Contacto.this, "Envio Exitoso", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Contacto.this, "Envio Fracaso", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }
}
