package com.android.cristiangallego.puppyshop.restApi.adapter;

import com.android.cristiangallego.puppyshop.restApi.ConstantesRestApi;
import com.android.cristiangallego.puppyshop.restApi.IEndpointApi;
import com.android.cristiangallego.puppyshop.restApi.deserializador.AmigosDeserializador;
import com.android.cristiangallego.puppyshop.restApi.deserializador.MascotaDeserializador;
import com.android.cristiangallego.puppyshop.restApi.deserializador.SearchDeserializador;
import com.android.cristiangallego.puppyshop.restApi.deserializador.SelfDeserializador;
import com.android.cristiangallego.puppyshop.restApi.model.AmigosResponse;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by USUARIO on 27/05/2017.
 */

public class RestApiAdapter {
    public IEndpointApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IEndpointApi.class);
    }

    public IEndpointApi establecerConexionRestApiInstagram(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IEndpointApi.class);
    }

    public Gson construyeGsonDeserializadorSelfMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUserMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUserFollowed(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AmigosResponse.class, new AmigosDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorSelfInfo(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new SelfDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorSearchInfo(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new SearchDeserializador());
        return gsonBuilder.create();
    }

    public IEndpointApi establecerConexionRestAPIHeroku(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IEndpointApi.class);
    };
}
