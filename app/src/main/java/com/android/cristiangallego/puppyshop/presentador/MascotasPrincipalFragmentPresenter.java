package com.android.cristiangallego.puppyshop.presentador;

import android.content.Context;

import com.android.cristiangallego.puppyshop.bd.BaseDatos;
import com.android.cristiangallego.puppyshop.fragmentos.IMascotasPrincipalFragmentView;
import com.android.cristiangallego.puppyshop.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by USUARIO on 19/04/2017.
 */

public class MascotasPrincipalFragmentPresenter implements IMascotasPrincipalFragmentPresenter {
    private IMascotasPrincipalFragmentView iMascotasPrincipalFragmentView;
    private Context context;
    private BaseDatos db;
    private ArrayList<Mascota> mascotas;

    public MascotasPrincipalFragmentPresenter(IMascotasPrincipalFragmentView iMascotasPrincipalFragmentView, Context context) {
        this.iMascotasPrincipalFragmentView = iMascotasPrincipalFragmentView;
        this.context = context;
        obteneMascotasBaseDeDatos();
    }

    @Override
    public void obteneMascotasBaseDeDatos() {
        this.db = new BaseDatos(context);
        mascotas = db.otenerListaPrincipal();
        mostrarMactoasRV();
    }

    @Override
    public void mostrarMactoasRV() {
        iMascotasPrincipalFragmentView.inicializarAdaptadorRV(iMascotasPrincipalFragmentView.crearAdaptador(mascotas));
        iMascotasPrincipalFragmentView.generarLayoutVertical();
    }
}
