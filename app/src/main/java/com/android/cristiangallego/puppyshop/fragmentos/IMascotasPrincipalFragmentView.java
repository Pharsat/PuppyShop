package com.android.cristiangallego.puppyshop.fragmentos;

import com.android.cristiangallego.puppyshop.adapter.MascotaAdaptador;
import com.android.cristiangallego.puppyshop.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by USUARIO on 19/04/2017.
 */

public interface IMascotasPrincipalFragmentView {

    MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    void inicializarAdaptadorRV(MascotaAdaptador adaptador);

    void generarLayoutVertical();

    void configuraMiMascota(Mascota setYoMismo);

}
