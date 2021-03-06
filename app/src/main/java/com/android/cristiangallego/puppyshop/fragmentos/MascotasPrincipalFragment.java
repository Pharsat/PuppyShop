package com.android.cristiangallego.puppyshop.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.adapter.MascotaAdaptador;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.presentador.IMascotasPrincipalFragmentPresenter;
import com.android.cristiangallego.puppyshop.presentador.MascotasPrincipalFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotasPrincipalFragment extends Fragment implements IMascotasPrincipalFragmentView {

    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;
    private IMascotasPrincipalFragmentPresenter presenter;
    private Mascota yoMismo;

    public MascotasPrincipalFragment() {

    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.yoMismo = mascotas.get(0);
        this.mascotas = mascotas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_mascotas_principal, container, false);
        this.rvMascotas = (RecyclerView) vista.findViewById(R.id.rvMascotas);
        presenter = new MascotasPrincipalFragmentPresenter(this,getContext());

        return vista;
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        return new MascotaAdaptador(mascotas, getActivity(), getContext(), yoMismo);
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public void configuraMiMascota(Mascota setYoMismo) {
        this.yoMismo = setYoMismo;
    }
}
