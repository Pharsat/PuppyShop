package com.android.cristiangallego.puppyshop.vistas;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.pojo.ElementosEnvioCorreo;
import com.android.cristiangallego.puppyshop.asincronos.EnviarCorreoAsyncTask;

public class Contacto extends AppCompatActivity {

    TextInputEditText txtNombre;
    TextInputEditText txtCorreo;
    TextInputEditText txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        txtNombre = (TextInputEditText) findViewById(R.id.txtNombre);
        txtCorreo = (TextInputEditText) findViewById(R.id.txtCorreo);
        txtMensaje = (TextInputEditText) findViewById(R.id.txtMensaje);
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
