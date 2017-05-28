package com.android.cristiangallego.puppyshop.restApi.deserializador;

import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.JsonKeys;
import com.android.cristiangallego.puppyshop.restApi.model.AmigosResponse;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by USUARIO on 27/05/2017.
 */

public class AmigosDeserializador implements JsonDeserializer<AmigosResponse> {
    @Override
    public AmigosResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        AmigosResponse amigosResponse = gson.fromJson(json, AmigosResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        amigosResponse.setClientesId(deserializarAmigosDeJson(contactoResponseData));
        return amigosResponse;
    }

    private ArrayList<String> deserializarAmigosDeJson(JsonArray contactoResponseData) {
        ArrayList<String> clientesId = new ArrayList<>();
        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseUser = contactoResponseData.get(i).getAsJsonObject();
            String id = contactoResponseUser.get(JsonKeys.USER_ID).getAsString();
            clientesId.add(id);
        }
        return clientesId;
    }
}
