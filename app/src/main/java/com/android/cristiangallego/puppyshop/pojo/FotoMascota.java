package com.android.cristiangallego.puppyshop.pojo;

import java.io.Serializable;

/**
 * Created by USUARIO on 9/04/2017.
 */

public class FotoMascota implements Serializable {
    private int nroFoto;
    private int nroLikes;
    private int id;

    public FotoMascota() {

    }

    public int getNroFoto() {
        return nroFoto;
    }

    public void setNroFoto(int nroFoto) {
        this.nroFoto = nroFoto;
    }

    public int getNroLikes() {
        return nroLikes;
    }

    public void setNroLikes(int nroLikes) {
        this.nroLikes = nroLikes;
    }

    public FotoMascota(int nroFoto, int nroLikes, int id) {
        this.nroFoto = nroFoto;
        this.nroLikes = nroLikes;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
