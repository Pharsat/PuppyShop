package com.android.cristiangallego.puppyshop.restApi.model;

/**
 * Created by USUARIO on 11/06/2017.
 */

public class BasicResponse {
    private Meta meta;
    private Object data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
