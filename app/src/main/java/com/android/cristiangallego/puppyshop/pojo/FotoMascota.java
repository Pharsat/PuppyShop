package com.android.cristiangallego.puppyshop.pojo;

import java.io.Serializable;

/**
 * Created by USUARIO on 9/04/2017.
 */

public class FotoMascota implements Serializable {
    private int nroFoto;
    private int nroLikes;
    private String id;
    private String url;

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

    public FotoMascota(int nroFoto, int nroLikes, String id) {
        this.nroFoto = nroFoto;
        this.nroLikes = nroLikes;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
