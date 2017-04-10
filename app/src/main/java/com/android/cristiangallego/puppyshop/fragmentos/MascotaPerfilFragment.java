package com.android.cristiangallego.puppyshop.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.adapter.FotoMascotaAdapter;
import com.android.cristiangallego.puppyshop.adapter.MascotaAdaptador;
import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotaPerfilFragment extends Fragment {

    private RecyclerView rvFotosDeLaMascotaActual;
    private Mascota mascota;
    private CircularImageView civPerfilMascota;
    private TextView tvNombreMascota;
    private View vista;

    public MascotaPerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_mascota_perfil, container, false);
        return vista;
    }

    public void InicializarAdaptador() {
        FotoMascotaAdapter contactoAdaptador = new FotoMascotaAdapter(mascota.getFotosSecundarias(), getActivity());
        rvFotosDeLaMascotaActual.setAdapter(contactoAdaptador);
    }

    public void RepintarPantalla(Mascota mascota) {
        this.mascota = mascota;
        if (mascota != null) {
            this.civPerfilMascota = (CircularImageView) vista.findViewById(R.id.civFotoMascota);
            this.tvNombreMascota = (TextView) vista.findViewById(R.id.tvNombreMascota);
            this.civPerfilMascota.setImageResource(mascota.getFotoPrincipalMascota().getNroFoto());
            this.tvNombreMascota.setText(mascota.getNombre());
            InicializarRecyclerView();
        }
    }

    private void InicializarRecyclerView() {
        this.rvFotosDeLaMascotaActual = (RecyclerView) vista.findViewById(R.id.rvFotosDeLaMascotaActual);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

        /*LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);*/

        this.rvFotosDeLaMascotaActual.setLayoutManager(glm);
        InicializarAdaptador();
    }
}
