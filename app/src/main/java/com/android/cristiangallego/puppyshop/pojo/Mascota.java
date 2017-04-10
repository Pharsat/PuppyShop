package com.android.cristiangallego.puppyshop.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by USUARIO on 2/04/2017.
 */

public class Mascota implements Serializable {

    public Mascota(FotoMascota fotoMascota, String nombre, int rating, boolean meGusta, ArrayList<FotoMascota> fotosSecundarias) {
        this.fotoPrincipalMascota = fotoMascota;
        this.nombre = nombre;
        this.rating = rating;
        this.meGusta = meGusta;
        this.fotosSecundarias = fotosSecundarias;
    }

    private FotoMascota fotoPrincipalMascota;
    private String nombre;
    private int rating;
    private boolean meGusta;
    private ArrayList<FotoMascota> fotosSecundarias;

    public FotoMascota getFotoPrincipalMascota() {
        return fotoPrincipalMascota;
    }

    public void setFotoPrincipalMascota(FotoMascota fotoPrincipalMascota) {
        this.fotoPrincipalMascota = fotoPrincipalMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }

    public ArrayList<FotoMascota> getFotosSecundarias() {
        return fotosSecundarias;
    }

    public void setFotosSecundarias(ArrayList<FotoMascota> fotosSecundarias) {
        this.fotosSecundarias = fotosSecundarias;
    }
}
