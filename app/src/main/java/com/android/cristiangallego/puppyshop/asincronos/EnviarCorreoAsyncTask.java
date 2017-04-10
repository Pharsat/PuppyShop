package com.android.cristiangallego.puppyshop.asincronos;

import android.os.AsyncTask;
import android.util.Log;

import com.android.cristiangallego.puppyshop.pojo.ElementosEnvioCorreo;
import com.android.cristiangallego.puppyshop.utilities.GMailSender;

/**
 * Created by USUARIO on 9/04/2017.
 */

public class EnviarCorreoAsyncTask extends AsyncTask<ElementosEnvioCorreo, Void, Boolean> {
    @Override
    protected Boolean doInBackground(ElementosEnvioCorreo... params) {
        try {
            GMailSender sender = new GMailSender("pharsatpruebas@gmail.com", "megustalainteligencia");
            sender.sendMail("PuppyShop Comentario",
                    "El señor: " + params[0].getNombre() + " te informa que '" + params[0].getMensaje() + "' responder al correo: " + params[0].getCorreo(),
                    "pharsat@gmail.com",
                    "pharsat@gmail.com");
            return true;
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
            return false;
        }
    }
}
