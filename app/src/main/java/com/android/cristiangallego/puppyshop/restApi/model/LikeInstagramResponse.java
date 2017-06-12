package com.android.cristiangallego.puppyshop.restApi.model;

/**
 * Created by USUARIO on 11/06/2017.
 */

public class LikeInstagramResponse extends UsuarioResponse {
    private String id_foto_instagram;
    private String id_mi_usuario;

    public LikeInstagramResponse() {
        super();
    }

    public LikeInstagramResponse(String id, String id_dispositivo, String id_usuario_instagram, String id_foto_instagram, String id_mi_usuario) {
        super(id, id_dispositivo, id_usuario_instagram);
        this.id_foto_instagram = id_foto_instagram;
        this.id_mi_usuario = id_mi_usuario;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }

    public String getId_mi_usuario() {
        return id_mi_usuario;
    }

    public void setId_mi_usuario(String id_mi_usuario) {
        this.id_mi_usuario = id_mi_usuario;
    }
}
