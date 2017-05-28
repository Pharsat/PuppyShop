package com.android.cristiangallego.puppyshop.presentador;

import java.util.ArrayList;

/**
 * Created by USUARIO on 19/04/2017.
 */

public interface IMascotasPrincipalFragmentPresenter {
    void obteneMascotasBaseDeDatos();
    void mostrarMactoasRV();
    void obtenerMediosRecientes(ArrayList<String> pendientesACargar);
    void consultarALosQueYoSigo();
}
