package com.android.cristiangallego.puppyshop.restApi.model;

/**
 * Created by USUARIO on 10/06/2017.
 */

public class UsuarioResponse {
    public String id;
    public String id_dispositivo;
    public String id_usuario_instagram;

    public UsuarioResponse(String id, String id_dispositivo, String id_usuario_instagram) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public UsuarioResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
}
