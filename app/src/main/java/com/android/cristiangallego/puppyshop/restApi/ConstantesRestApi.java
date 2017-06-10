package com.android.cristiangallego.puppyshop.restApi;

/**
 * Created by USUARIO on 27/05/2017.
 */

public class ConstantesRestApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "1530547347.ea88bd5.cdfe8887ed0e4b77acd8a6f4c1398bb0";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_ACCESS_TOKEN_2 = "access_token=";
    public static final String KEY_GET_INFORMATION_USER = "users/self/media/recent/";
    public static final String KEY_GET_FOLLOWERS = "users/self/follows";
    public static final String KEY_GET_USER_RECENT_MEDIA = "users/{user-id}/media/recent/";
    public static final String KEY_USER_ID = "1530547347";
    public static final String KEY_GET_SELF_INFO = "users/" + KEY_USER_ID + "/";
    public static final String KEY_GET_SEARCH_INFO = "users/search?q={query}&";

    public static final String URL_GET_SELF_RECENT_MEDIA = KEY_GET_INFORMATION_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_IFOLLOW = KEY_GET_FOLLOWERS + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_USER_RECENT_MEDIA = KEY_GET_USER_RECENT_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_SELF_INFO = KEY_GET_SELF_INFO + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_SEARCH_INFO = KEY_GET_SEARCH_INFO + KEY_ACCESS_TOKEN_2 + ACCESS_TOKEN;

    /*https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN*/
    /*https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN*/
    /*https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN*/
    /*https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN*/


    public static final String ROOT_URL_HEROKU = "https://stormy-shelf-39014.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario/";
}

