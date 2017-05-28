package com.android.cristiangallego.puppyshop.restApi.deserializador;

import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
import com.android.cristiangallego.puppyshop.pojo.Mascota;
import com.android.cristiangallego.puppyshop.restApi.JsonKeys;
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
 * Created by USUARIO on 28/05/2017.
 */

public class SearchDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(deserializarMascotasDeJson(contactoResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializarMascotasDeJson(JsonArray contactoResponseData) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseUser = contactoResponseData.get(i).getAsJsonObject();
            String id = contactoResponseUser.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = contactoResponseUser.get(JsonKeys.FULL_NAME).getAsString();
            String url = contactoResponseUser.get(JsonKeys.MEDIA_PROFILEPIC).getAsString();
            Mascota contacto = new Mascota();
            contacto.setId(id);
            contacto.setNombre(nombreCompleto);
            contacto.setFotoPrincipalMascota(new FotoMascota());
            contacto.getFotoPrincipalMascota().setUrl(url);
            mascotas.add(contacto);
        }
        return mascotas;
    }
}