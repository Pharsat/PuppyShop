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
 * Created by USUARIO on 27/05/2017.
 */

public class SelfDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonObject contactoResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(deserializarMascotasDeJson(contactoResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializarMascotasDeJson(JsonObject contactoResponseData) {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String id = contactoResponseData.get(JsonKeys.USER_ID).getAsString();
        String nombreCompleto = contactoResponseData.get(JsonKeys.FULL_NAME).getAsString();
        String url = contactoResponseData.get(JsonKeys.MEDIA_PROFILEPIC).getAsString();
        JsonObject likesObject = contactoResponseData.getAsJsonObject(JsonKeys.MEDIA_COUNTS);
        int likes = likesObject.get(JsonKeys.MEDIA_COUNTS_MEDIA).getAsInt();

        Mascota contacto = new Mascota();
        contacto.setId(id);
        contacto.setNombre(nombreCompleto);
        contacto.setFotoPrincipalMascota(new FotoMascota());
        contacto.getFotoPrincipalMascota().setNroLikes(likes);
        contacto.getFotoPrincipalMascota().setUrl(url);
        contacto.setFotosSecundarias(new ArrayList<FotoMascota>());
        mascotas.add(contacto);

        return mascotas;
    }

}
