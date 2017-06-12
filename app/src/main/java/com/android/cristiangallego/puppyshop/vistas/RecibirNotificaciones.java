package com.android.cristiangallego.puppyshop.vistas;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.adapter.RestApiAdapter;
import com.android.cristiangallego.puppyshop.restApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecibirNotificaciones extends AppCompatActivity {

    private static final String TAG = "RecibirNotificaciones";

    private Button bBuscar;

    private FloatingActionButton fabCamera;
    private Toolbar tbBarraHerramientas;
    private TextView tvCuentaconfigurada;
    private TextView tvTitulo;

    private Mascota idClienteACargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibir_notificaciones);

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
                String token = FirebaseInstanceId.getInstance().getToken();

                // Log and toast
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                IEndpointApi endpoints = restApiAdapter.establecerConexionRestAPIHeroku();
                Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenId(token, idClienteACargar.getId());
                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse usuarioResponse = response.body();
                        String msg = getString(R.string.msg_token_fmt, usuarioResponse.getId_dispositivo());
                        Log.d(TAG, msg);
                        Toast.makeText(RecibirNotificaciones.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                        Toast.makeText(RecibirNotificaciones.this, "Fallo :(", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            if (parametros.containsKey(getResources().getString(R.string.perfilACargar))) {
                this.idClienteACargar = (Mascota) parametros.get(getResources().getString(R.string.perfilACargar));
            }
        }
        this.bBuscar.setEnabled(this.idClienteACargar != null);
        if(this.idClienteACargar != null) {
            this.tvCuentaconfigurada.setText("Nombre: " + this.idClienteACargar.getNombre() + " Id: " + this.idClienteACargar.getId());
        }
    }

    public void instanciarDiseno() {
        this.bBuscar = (Button) findViewById(R.id.bConfigura);
        this.tbBarraHerramientas = (Toolbar) findViewById(R.id.miActionBar);
        this.fabCamera = (FloatingActionButton) findViewById(R.id.fabCamera);
        this.tvTitulo = (TextView) tbBarraHerramientas.findViewById(R.id.tvTitulo);
        this.tvCuentaconfigurada = (TextView) findViewById(R.id.cuentaConfigurada);
    }
}
