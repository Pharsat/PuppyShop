package com.android.cristiangallego.puppyshop.restApi;

import com.android.cristiangallego.puppyshop.restApi.model.AmigosResponse;
import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
