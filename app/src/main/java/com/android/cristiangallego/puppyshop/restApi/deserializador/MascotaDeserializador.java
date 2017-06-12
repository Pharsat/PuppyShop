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

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

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
            JsonObject userJson = contactoResponseUser.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.FULL_NAME).getAsString();
            JsonObject mediaImages = contactoResponseUser.getAsJsonObject(JsonKeys.MEDIA_IMAGE);
            JsonObject standardResolution = mediaImages.getAsJsonObject(JsonKeys.MEDIA_STRANDARD_RESOLUTION);
            String url = standardResolution.get(JsonKeys.MEDIA_URL).getAsString();
            JsonObject likesObject = contactoResponseUser.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesObject.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();
            String idFoto = contactoResponseUser.get("id").getAsString();
            Mascota contacto = new Mascota();
            contacto.setId(id);
            contacto.setNombre(nombreCompleto);
            contacto.setFotoPrincipalMascota(new FotoMascota());
            contacto.getFotoPrincipalMascota().setNroLikes(likes);
            contacto.getFotoPrincipalMascota().setUrl(url);
            contacto.getFotoPrincipalMascota().setId(idFoto);
            mascotas.add(contacto);
        }
        return mascotas;
    }

}
