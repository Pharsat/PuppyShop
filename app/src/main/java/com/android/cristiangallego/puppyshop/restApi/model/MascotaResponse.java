package com.android.cristiangallego.puppyshop.restApi.model;

import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
import com.android.cristiangallego.puppyshop.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by USUARIO on 27/05/2017.
 */

public class MascotaResponse {
    private ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public ArrayList<FotoMascota> getFotoMascotas() {
        ArrayList<FotoMascota> fotoMascotas = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            FotoMascota fotoMascota = new FotoMascota();
            fotoMascota.setNroLikes(mascota.getFotoPrincipalMascota().getNroLikes());
            fotoMascota.setUrl(mascota.getFotoPrincipalMascota().getUrl());
            fotoMascota.setId(mascota.getId());
            fotoMascotas.add(fotoMascota);
        }
        return fotoMascotas;
    }
}
