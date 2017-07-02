package com.android.cristiangallego.puppyshop.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.vistas.Contacto;
import com.android.cristiangallego.puppyshop.vistas.MainActivity;

/**
 * Created by USUARIO on 1/07/2017.
 */

public class Broadcaster extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intento = null;
        switch (intent.getAction()) {
            case "PERFIL"://intent.getAction()
                intento = new Intent(context, MainActivity.class);
                Toast.makeText(context, "Entraste al perfil", Toast.LENGTH_SHORT).show();
                break;
            case "FOLLOW"://intent.getAction()
                intento = new Intent(context, MainActivity.class);
                Toast.makeText(context, "Seguiste al user", Toast.LENGTH_SHORT).show();
                break;
            case "RAITEADOR"://intent.getAction()
                intento = new Intent(context, MainActivity.class);
                Toast.makeText(context, "Raiteaste Al user", Toast.LENGTH_SHORT).show();
                break;
        }
        if (intent != null) {
            context.startActivity(intento);
        }
    }
}
