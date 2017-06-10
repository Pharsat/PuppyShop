package com.android.cristiangallego.puppyshop.restApi;

import com.android.cristiangallego.puppyshop.restApi.model.AmigosResponse;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;
import com.android.cristiangallego.puppyshop.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by USUARIO on 27/05/2017.
 */

public interface IEndpointApi {
    @GET(ConstantesRestApi.URL_GET_SELF_RECENT_MEDIA)
    Call<MascotaResponse> getRecentSelfMedia();

    @GET(ConstantesRestApi.URL_GET_IFOLLOW)
    Call<AmigosResponse> getFolloweds();

    @GET()
    Call<MascotaResponse> getRecentUserMedia(@Url String query);

    @GET(ConstantesRestApi.URL_GET_SELF_INFO)
    Call<MascotaResponse> getSelfInformation();

    @GET()
    Call<MascotaResponse> getSearchInformation(@Url String query);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenId(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);
}
