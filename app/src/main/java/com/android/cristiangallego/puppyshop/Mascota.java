package com.android.cristiangallego.puppyshop;

import java.io.Serializable;

/**
 * Created by USUARIO on 2/04/2017.
 */

public class Mascota implements Serializable {

    public Mascota(int fotoMascota, String nombre, int rating, boolean meGusta) {
        this.fotoMascota = fotoMascota;
        this.nombre = nombre;
        this.rating = rating;
        this.meGusta = meGusta;
    }

    private int fotoMascota;
    private String nombre;
    private int rating;
    private boolean meGusta;

    public int getFotoMascota() {
        return fotoMascota;
    }

    public void setFotoMascota(int fotoMascota) {
        this.fotoMascota = fotoMascota;
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


}
