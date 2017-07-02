package com.android.cristiangallego.puppyshop.restApi.model;

/**
 * Created by USUARIO on 1/07/2017.
 */

public class LikeInstagramCollectionResponse {
    public LikeInstagramCollectionResponse(LikeInstagramResponse[] respuestas) {
        this.respuestas = respuestas;
    }

    private LikeInstagramResponse[] respuestas;

    public LikeInstagramResponse[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(LikeInstagramResponse[] respuestas) {
        this.respuestas = respuestas;
    }
}
