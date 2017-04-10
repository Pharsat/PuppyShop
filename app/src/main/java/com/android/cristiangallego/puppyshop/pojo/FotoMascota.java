package com.android.cristiangallego.puppyshop.pojo;

import java.io.Serializable;

/**
 * Created by USUARIO on 9/04/2017.
 */

public class FotoMascota implements Serializable {
    private int nroFoto;

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

    private int nroLikes;

    public FotoMascota(int nroFoto, int nroLikes) {
        this.nroFoto = nroFoto;
        this.nroLikes = nroLikes;
    }
}
