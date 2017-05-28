package com.android.cristiangallego.puppyshop.restApi.model;

import com.android.cristiangallego.puppyshop.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by USUARIO on 27/05/2017.
 */

public class AmigosResponse {
    private ArrayList<String> clientesId;

    public ArrayList<String> getClientesId() {
        return clientesId;
    }

    public void setClientesId(ArrayList<String> clientesId) {
        this.clientesId = clientesId;
    }
}
