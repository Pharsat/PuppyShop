package com.android.cristiangallego.puppyshop.restApi;

import com.android.cristiangallego.puppyshop.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by USUARIO on 27/05/2017.
 */

public interface IEndpointApi {
    @GET(ConstantesRestApi.URL_GET_SELF_RECENT_MEDIA)
    Call<MascotaResponse> getRecentSelfMedia();

    @GET(ConstantesRestApi.URL_GET_FOLLOWERS)
    Call<MascotaResponse> getFollowers();

    @GET(ConstantesRestApi.URL_GET_USER_RECENT_MEDIA)
    Call<MascotaResponse> getRecentUserMedia();

    @GET(ConstantesRestApi.URL_GET_SELF_INFO)
    Call<MascotaResponse> getSelfInformation();
}
