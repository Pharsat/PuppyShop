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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotasPrincipalFragment extends Fragment {

    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;

    public MascotasPrincipalFragment() {
        // Required empty public constructor
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_mascotas_principal, container, false);

        this.rvMascotas = (RecyclerView) vista.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);
        InicializarAdaptador();

        return vista;
    }

    public void InicializarAdaptador() {
        MascotaAdaptador contactoAdaptador = new MascotaAdaptador(mascotas, getActivity(), getContext());
        rvMascotas.setAdapter(contactoAdaptador);
    }
}
